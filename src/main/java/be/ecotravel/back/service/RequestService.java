package be.ecotravel.back.service;

import be.ecotravel.back.entity.*;
import be.ecotravel.back.repository.RequestRepository;
import be.ecotravel.back.repository.UserRepository;
import be.ecotravel.back.request.dto.RequestCreationDto;
import be.ecotravel.back.request.dto.RequestPutDto;
import be.ecotravel.back.request.dto.RequestResponseDto;
import be.ecotravel.back.request.mapper.RequestMapper;
import be.ecotravel.back.util.BitFieldUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class RequestService {

    private final RequestRepository requestRepo;
    private final UserRepository userRepo;
    private final RequestMapper requestMapper;
    private final CloudinaryService cloudinaryService;
    private final EmailService emailService;

    @Autowired
    public RequestService(
            RequestRepository requestRepository,
            UserRepository userRepository,
            RequestMapper requestMapper,
            CloudinaryService cloudinaryService,
            EmailService emailService
    ) {
        this.requestRepo = requestRepository;
        this.userRepo = userRepository;
        this.requestMapper = requestMapper;
        this.cloudinaryService = cloudinaryService;
        this.emailService = emailService;
    }

    public UUID createRequest(RequestCreationDto requestDto) {
        User user = userRepo.findById(requestDto.userId())
                .orElseThrow(EntityNotFoundException::new);

        String[] activitiesSource = Arrays.stream(DestinationTypeEnum.values())
                .map(Enum::name)
                .toArray(String[]::new);
        int serviceNumber = BitFieldUtils.toNumber(activitiesSource, requestDto.services());



        HostStatusEnum hostStatus = HostStatusEnum.valueOf(requestDto.status().toUpperCase());

        Request request = requestMapper.toEntity(requestDto, serviceNumber, user, hostStatus);

        requestRepo.save(request);

        return request.getId();
    }

    public List<RequestResponseDto> getAllRequests() {
        List<Request> requests = requestRepo.findByRequestStatus(RequestStatusEnum.WAITING);
        List<RequestResponseDto> requestResponses = new ArrayList<>(requests.size());

        String[] activitiesSource = Arrays.stream(DestinationTypeEnum.values())
                .map(Enum::name)
                .toArray(String[]::new);

        for (Request request : requests) {
            User user = request.getUser();
            String fullName = user.getFirstName() + " " + user.getLastName();

            String[] services = Arrays.stream(BitFieldUtils.toArray(activitiesSource, request.getServices()))
                    .map(service -> DestinationTypeEnum.valueOf(service).getValue())
                    .toArray(String[]::new);

            List<String> fileUrl;
            try {
                fileUrl = cloudinaryService.getFileFromFolder("certificationFile/" + request.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            String hostStatus = request.getHostStatus().getValue();

            RequestResponseDto requestResponseDto = requestMapper.toResponseDto(request, hostStatus, fullName, user.getEmail(), services, fileUrl);
            requestResponses.add(requestResponseDto);
        }

        return requestResponses;
    }

    public List<RequestResponseDto> updateRequest(UUID id, RequestPutDto dto) {
        RequestStatusEnum status = RequestStatusEnum.valueOf(dto.status());
        Optional<Request> requestOptional = requestRepo.findById(id);

        if (requestOptional.isEmpty()) {
            throw new EntityNotFoundException("Aucune requête avec cette destinationID");
        }

        Request request = requestOptional.get();

        User user = request.getUser();

        request.setRequestStatus(status);
        requestRepo.save(request);

        String emailContent =
                "<html>" +
                        "<body>" +
                        "<p>Bonjour,</p>" +
                        "<p>Merci d'avoir fait la demande pour devenir hébergeur sur EcoTravel!'</p>" +
                        "<p>Votre demande à malheureusement été refusée..</p>" +
                        "<p>Voici la raison :</p>" +
                        "<p>" + dto.message() + "</p>" +
                        "<p>Nous vous souhaitons une belle journée!</p>" +
                        "<p>L'équipe EcoTravel</p>" +
                        "</body>" +
                        "</html>";

        if(dto.status().equals("ACCEPTED")){
            emailService.sendEmail("lucas@ecotravel.com", user.getEmail(), "Votre demande de compte hôte à été acceptée", dto.message());
        }else{
            emailService.sendEmail("lucas@ecotravel.com", user.getEmail(), "Votre demande de compte hôte à été refusé", emailContent);
        }

        return getAllRequests();
    }

    public List<String> addCertificationFile(String requestId, MultipartFile file) {
        List<String> imageUrls = new ArrayList<>();

        try {
            String originalFilename = file.getOriginalFilename();

            String filenameWithoutExtension = originalFilename != null ? originalFilename.substring(0, originalFilename.lastIndexOf('.')) : originalFilename;

            String imageUrl = cloudinaryService.uploadFileToFolder(file, "certificationFile/" + requestId, filenameWithoutExtension);

            imageUrls.add(imageUrl);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors du téléchargement du fichier : " + file.getOriginalFilename(), e);
        }

        return imageUrls;
    }

    public RequestStatusEnum getRequestStatusFromUser(UUID userId) {
        User user = userRepo.findUserById(userId)
                .orElseThrow(EntityNotFoundException::new);

        Optional<Request> requestOptional = this.requestRepo.findByUser(user);

        return requestOptional.map(Request::getRequestStatus).orElse(null);
    }
}
