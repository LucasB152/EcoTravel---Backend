package be.ecotravel.back.service;

import be.ecotravel.back.destination.dto.DestinationCreationDto;
import be.ecotravel.back.destination.dto.DestinationSearchDto;
import be.ecotravel.back.destination.dto.DestinationDetailsDto;
import be.ecotravel.back.destination.mapper.DestinationMapper;
import be.ecotravel.back.entity.*;
import be.ecotravel.back.repository.DestinationRepository;
import be.ecotravel.back.repository.DestinationTypeRepository;
import be.ecotravel.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DestinationService {

    private final DestinationMapper destinationMapper; //Si ne fonctionne pas, mettre en protected
    private final SearchService searchService;
    private final DestinationRepository destinationRepo;
    private final DestinationTypeRepository destinationTypeRepo;
    private final UserRepository userRepo;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public DestinationService(
            DestinationMapper destinationMapper,
            DestinationRepository destinationRepo,
            DestinationTypeRepository destinationTypeRepo,
            UserRepository userRepo,
            SearchService searchService,
            CloudinaryService cloudinaryService
    ) {
        this.destinationMapper = destinationMapper;
        this.destinationRepo = destinationRepo;
        this.destinationTypeRepo = destinationTypeRepo;
        this.userRepo = userRepo;
        this.searchService = searchService;
        this.cloudinaryService = cloudinaryService;
    }

    public DestinationDetailsDto getDestinationDetails(UUID id) {
        Destination destination = destinationRepo.findById(id).orElse(null);

        if (destination == null) {
            return null;
        }

        //todo changer try catch quand cloudinary sera ok
        List<String> images = List.of(destination.getImageFolderPath());
        try {
            images = cloudinaryService.getImagesFromFolder(destination.getImageFolderPath());
        } catch (Exception ignored) {

        }

        //todo utiliser un mapper
        return new DestinationDetailsDto(
                destination.getId(),
                destination.getName(),
                destination.getDescription(),
                destination.getPrice(),
                destination.getCapacity(),
                destination.getContactPhone(),
                destination.getContactEmail(),
                images,
                destination.getDestinationType().getType().name(),
                destination.getAddress().toString(),
                destination.getTag().stream().map(Tag::getName).collect(Collectors.toList()),
                destination.isVisible()
        );
    }

    public Page<DestinationSearchDto> searchDestinations(String query, List<String> tags, String type, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<Destination> destinationsPage = searchService.searchDestinations(query, tags, type, pageRequest);

        return destinationsPage.map(destinationMapper::toSearchDto);
    }

    public UUID createDestination(DestinationCreationDto destinationDto) {
        DestinationTypeEnum typeEnum = DestinationTypeEnum.valueOf(destinationDto.destinationType());
        DestinationType type = destinationTypeRepo.findById(typeEnum.ordinal()).orElseThrow();
        //TODO S'occuper des exceptions ?

        User user = userRepo.findUserById(destinationDto.userId()).orElseThrow();

        Destination destination = destinationMapper.toEntity(destinationDto, type, user);

        destinationRepo.save(destination);
        return destination.getId();
    }

    public List<String> getDestinationTypes() {
        return destinationTypeRepo.getAllTypes();
    }
}
