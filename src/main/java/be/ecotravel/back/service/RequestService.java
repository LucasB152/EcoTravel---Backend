package be.ecotravel.back.service;

import be.ecotravel.back.entity.Request;
import be.ecotravel.back.entity.RequestStatusEnum;
import be.ecotravel.back.entity.User;
import be.ecotravel.back.repository.RequestRepository;
import be.ecotravel.back.repository.UserRepository;
import be.ecotravel.back.request.dto.RequestCreationDto;
import be.ecotravel.back.request.dto.RequestPutDto;
import be.ecotravel.back.request.dto.RequestResponseDto;
import be.ecotravel.back.request.mapper.RequestMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    private final RequestRepository requestRepo;

    private final UserRepository userRepo;

    private final RequestMapper requestMapper;

    @Autowired
    public RequestService(
            RequestRepository requestRepository,
            UserRepository userRepository,
            RequestMapper requestMapper
    ) {
        this.requestRepo = requestRepository;
        this.userRepo = userRepository;
        this.requestMapper = requestMapper;
    }

    public void createRequest(RequestCreationDto requestDto) {
        User user = userRepo.getReferenceById(requestDto.userId());
        Request request = new Request(requestDto.text(), RequestStatusEnum.WAITING, user);
        requestRepo.save(request);
    }

    public List<RequestResponseDto> getAllRequests() {
        List<Request> requests = requestRepo.findAll();
        List<RequestResponseDto> requestResponses = new ArrayList<>(requests.size());

        for (Request request : requests) {
            User user = request.getUser();
            String fullName = user.getFirstname() + " " + user.getLastname();
            String status = request.getStatus().name();

            RequestResponseDto requestResponseDto = requestMapper.toResponseDto(request, fullName, status);
            requestResponses.add(requestResponseDto);
        }

        return requestResponses;
    }

    public void updateRequest(RequestPutDto dto) {
        RequestStatusEnum status = RequestStatusEnum.valueOf(dto.status());
        Optional<Request> requestOptional = requestRepo.findById(dto.id());

        if (requestOptional.isEmpty()) {
            throw new EntityNotFoundException("Aucune requête avec cette id");
        }

        Request request = requestOptional.get();
        request.setStatus(status);
        requestRepo.save(request); //TODO Save ou se fait automatiquement avec l'orm ?
    }
}
