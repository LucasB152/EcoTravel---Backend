package be.ecotravel.back.service;

import be.ecotravel.back.destination.dto.DestinationCreationDto;
import be.ecotravel.back.destination.dto.DestinationOnSearchDto;
import be.ecotravel.back.destination.dto.SearchCriteria;
import be.ecotravel.back.destination.mapper.DestinationMapper;
import be.ecotravel.back.entity.Destination;
import be.ecotravel.back.entity.DestinationType;
import be.ecotravel.back.entity.DestinationTypeEnum;
import be.ecotravel.back.entity.User;
import be.ecotravel.back.repository.DestinationRepository;
import be.ecotravel.back.repository.DestinationTypeRepository;
import be.ecotravel.back.repository.UserRepository;
import be.ecotravel.back.search.QuerySearchCriteriaStrategy;
import be.ecotravel.back.search.SearchCriteriaStrategy;
import be.ecotravel.back.search.TagSearchCriteriaStrategy;
import be.ecotravel.back.search.TypeSearchCriteriaStrategy;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public DestinationService(
            DestinationMapper destinationMapper,
            DestinationRepository destinationRepo,
            DestinationTypeRepository destinationTypeRepo,
            UserRepository userRepo,
            SearchService searchService
    ) {
        this.destinationMapper = destinationMapper;
        this.destinationRepo = destinationRepo;
        this.destinationTypeRepo = destinationTypeRepo;
        this.userRepo = userRepo;
        this.searchService = searchService;
    }

    public List<Destination> getPopular() {
        return this.destinationRepo.findAll();
    }


    /**
     * Client de la stratégie de recherche
     */
    public List<DestinationOnSearchDto> searchDestinations(SearchCriteria searchCriteria) {

        List<SearchCriteriaStrategy> criteriaList = List.of(
                new QuerySearchCriteriaStrategy(searchCriteria.query()),
                new TagSearchCriteriaStrategy(searchCriteria.tags()),
                new TypeSearchCriteriaStrategy(searchCriteria.type())
        );

        List<Destination> destinations = searchService.search(criteriaList);
        System.out.println("[DEBUG] " + destinations);
        //todo: utiliser un mapper
        return destinations.stream()
                .map(destination -> new DestinationOnSearchDto(
                        destination.getId(),
                        destination.getName(),
                        destination.getDescription(),
                        destination.getAddress().getLatitude(),
                        destination.getAddress().getLongitude(),
                        destination.getAddress().getStreet(),
                        destination.getDestinationType().getType().name(),
                        List.of("image1", "image2", "image3"),
                        destination.getTags().stream().map(tag -> tag.getName()).collect(Collectors.toList())
                ))
                .collect(Collectors.toList());

    }

    public Destination getDestinationById(UUID id) {
        return destinationRepo.findById(id).orElse(null);
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
}
