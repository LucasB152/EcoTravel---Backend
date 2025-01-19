package be.ecotravel.back.service;

import be.ecotravel.back.destination.dto.DestinationCreationDto;
import be.ecotravel.back.destination.dto.DestinationResponseDto;
import be.ecotravel.back.destination.dto.DestinationSearchDto;
import be.ecotravel.back.destination.dto.DestinationDetailsDto;
import be.ecotravel.back.destination.mapper.DestinationMapper;
import be.ecotravel.back.entity.*;
import be.ecotravel.back.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DestinationService {

    private final DestinationMapper destinationMapper;
    private final SearchService searchService;
    private final DestinationRepository destinationRepository;
    private final DestinationTypeRepository destinationTypeRepository;
    private final UserRepository userRepository;
    private final CloudinaryService cloudinaryService;
    private final TagRepository tagRepository;
    private final AddressService addressService;

    @Autowired
    public DestinationService(
            DestinationMapper destinationMapper,
            DestinationRepository destinationRepo,
            DestinationTypeRepository destinationTypeRepo,
            TagRepository tagRepository,
            UserRepository userRepo,
            SearchService searchService,
            CloudinaryService cloudinaryService,
            AddressService addressService
    ) {
        this.destinationMapper = destinationMapper;
        this.destinationRepository = destinationRepo;
        this.destinationTypeRepository = destinationTypeRepo;
        this.userRepository = userRepo;
        this.searchService = searchService;
        this.cloudinaryService = cloudinaryService;
        this.tagRepository = tagRepository;
        this.addressService = addressService;
    }

    public DestinationDetailsDto getDestinationDetails(UUID id) {
        Destination destination = destinationRepository.findById(id).orElse(null);

        if (destination == null) {
            return null;
        }

        List<String> images = Collections.emptyList();
        try {
            images = cloudinaryService.getFileFromFolder("destinationPicture/" + id);
        } catch (Exception ignored) {

        }
        DestinationDetailsDto destinationDetailDto = destinationMapper.toDetailsDto(destination);
        destinationDetailDto.setImages(images);
        destinationDetailDto.setTags(destination.getTag().stream().map(Tag::getName).collect(Collectors.toList()));
        return destinationDetailDto;
    }

    public Page<DestinationSearchDto> searchDestinations(String query, List<String> tags, String type, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<Destination> destinationsPage = searchService.searchDestinations(query, tags, type, pageRequest);

        return destinationsPage.map(destination -> {
            List<String> images;
            DestinationSearchDto destinationSearchDto = null;
            try {
                images = cloudinaryService.getFileFromFolder("destinationPicture/" + destination.getId());
                destinationSearchDto = destinationMapper.toSearchDto(destination);
                destinationSearchDto.setImages(images);
            } catch (Exception ignored) {

            }
            return destinationSearchDto;
        });
    }

    public UUID createDestination(DestinationCreationDto destinationDto, UUID userId) {
        DestinationTypeEnum typeEnum = DestinationTypeEnum.valueOf(destinationDto.destinationType());
        DestinationType type = destinationTypeRepository.findByType(typeEnum).orElseThrow(EntityNotFoundException::new);

        User user = userRepository.findUserById(userId).orElseThrow();

        Set<Tag> tags = new HashSet<>();
        for(String tagId : destinationDto.tagsId()){
            Tag tag = tagRepository.findById(UUID.fromString(tagId))
                    .orElseThrow(EntityNotFoundException::new);
            tags.add(tag);
        }

        Address address = addressService.createAddress(destinationDto.country(),  destinationDto.location(), destinationDto.street(), destinationDto.number(), destinationDto.zipcode());

        Destination destination = destinationMapper.toEntity(destinationDto, type, user, tags, address);

        destinationRepository.save(destination);
        return destination.getId();
    }

    public List<String> getDestinationTypes() {
        return destinationTypeRepository.getAllTypes();
    }

    public List<DestinationResponseDto> getDestinationFromHost(UUID hostId) {
        User host = userRepository.findUserById(hostId)
                .orElseThrow(EntityNotFoundException::new);

        return destinationRepository.findAllByUser(host).stream().map(
                destinationMapper::toDestinationResponseDto
        ).toList();

    }

    public Object addDestinationPicture(UUID destinationId, MultipartFile file) {
        List<String> imageUrls = new ArrayList<>();
        try {
            String originalFilename = file.getOriginalFilename();

            String filenameWithoutExtension = originalFilename != null ? originalFilename.substring(0, originalFilename.lastIndexOf('.')) : originalFilename;

            String imageUrl = cloudinaryService.uploadFileToFolder(file, "destinationPicture/" + destinationId, filenameWithoutExtension);

            imageUrls.add(imageUrl);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors du téléchargement du fichier : " + file.getOriginalFilename(), e);
        }

        return imageUrls;

    }

    public void deleteDestination(UUID destinationId) {
        Destination destination = destinationRepository.findById(destinationId)
                .orElseThrow(EntityNotFoundException::new);

        destinationRepository.delete(destination);
    }

    public void modifyDestination(UUID destinationId, DestinationCreationDto updatedDestination) {
        Destination destination = destinationRepository.findById(destinationId)
                .orElseThrow(EntityNotFoundException::new);

        DestinationTypeEnum typeEnum = DestinationTypeEnum.valueOf(updatedDestination.destinationType());
        DestinationType type = destinationTypeRepository.findByType(typeEnum).orElseThrow(EntityNotFoundException::new);

        Set<Tag> tags = new HashSet<>();
        for(String tagId : updatedDestination.tagsId()){
            Tag tag = tagRepository.findById(UUID.fromString(tagId))
                    .orElseThrow(EntityNotFoundException::new);
            tags.add(tag);
        }

        Address address = addressService.createAddress(updatedDestination.country(),  updatedDestination.location(), updatedDestination.street(), updatedDestination.number(), updatedDestination.zipcode());


        destination.setName(updatedDestination.name());
        destination.setDescription(updatedDestination.description());
        destination.setCapacity(updatedDestination.capacity());
        destination.setContactEmail(updatedDestination.contactEmail());
        destination.setContactPhone(updatedDestination.contactPhone());
        destination.setDestinationType(type);
        destination.setAddress(address);
        destination.setPrice(updatedDestination.price());
        destination.setTag(tags);

        destinationRepository.save(destination);
    }
}
