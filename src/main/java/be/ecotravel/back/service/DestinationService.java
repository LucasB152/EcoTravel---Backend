package be.ecotravel.back.service;

import be.ecotravel.back.destination.dto.DestinationCreationDto;
import be.ecotravel.back.destination.dto.DestinationOnSearchDto;
import be.ecotravel.back.destination.dto.DestinationResponseDto;
import be.ecotravel.back.destination.dto.SearchCriteria;
import be.ecotravel.back.destination.mapper.DestinationMapper;
import be.ecotravel.back.entity.Destination;
import be.ecotravel.back.entity.DestinationType;
import be.ecotravel.back.entity.DestinationTypeEnum;
import be.ecotravel.back.entity.User;
import be.ecotravel.back.repository.DestinationRepository;
import be.ecotravel.back.repository.DestinationTypeRepository;
import be.ecotravel.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class DestinationService {

    private final DestinationMapper destinationMapper; //Si ne fonctionne pas, mettre en protected

    private final DestinationRepository destinationRepo;
    private final DestinationTypeRepository destinationTypeRepo;
    private final UserRepository userRepo;

    @Autowired
    public DestinationService(
            DestinationMapper destinationMapper,
            DestinationRepository destinationRepo,
            DestinationTypeRepository destinationTypeRepo,
            UserRepository userRepo
    ) {
        this.destinationMapper = destinationMapper;
        this.destinationRepo = destinationRepo;
        this.destinationTypeRepo = destinationTypeRepo;
        this.userRepo = userRepo;
    }

    public List<Destination> getPopular() {
        return this.destinationRepo.findAll();
    }


    /**
     * Recherche des destinations complètes avec plusieur table liée au destination
     *
     * !!! ATTENTION pas encore implémentée mais permet de faire des tris temporaire pour la recherche!!!
     *
     * @param searchCriteria critère de recherche recu par les paramètres de l'url
     * @return
     */
    public List<DestinationOnSearchDto> searchDestinations(SearchCriteria searchCriteria) {
        //TODO Implementer la recherche

        List<DestinationOnSearchDto> TEMPDestinationAll = List.of(
                new DestinationOnSearchDto(
                        UUID.randomUUID(),
                        "maison en foret",
                        "tres tres jolie maison en foret avec les ours",
                        50.7636,
                        5.5273,
                        "rue de la foret, 1 4000 Liege",
                        "LODGING",
                        List.of("https://www.houseplans.net/uploads/plans/32005/elevations/88909-768.jpg","https://casaeconstrucao.org/wp-content/uploads/2020/03/casas-baratas-tiny-house-no-jardim.jpg"),
                        List.of("wifi", "swimmpool", "all-in", "no pet", "no smoke")
                ),
                new DestinationOnSearchDto(
                        UUID.randomUUID(),
                        "acrobranche",
                        "acrobranche AGILE dans les arbres",
                        50.66036,
                        5.5993,
                        "rue de l'arbre, 37 4000 Visé",
                        "ACTIVITY",
                        List.of("https://ecopark-adventures.com/wp-content/uploads/2019/07/HP-Main-picture-Tournai-e1580480117562.jpg"),
                        List.of("wifi", "swimmpool", "all-in", "no pet", "no smoke")
                ),
                new DestinationOnSearchDto(
                        UUID.randomUUID(),
                        "hotel super éco",
                        "hotel super éco avec des chambres en bois",
                        50.7336,
                        5.2273,
                        "rue de l'hotel, 24 4671 Saive",
                        "LODGING",
                        List.of("https://casaeconstrucao.org/wp-content/uploads/2020/03/casas-baratas-tiny-house-no-jardim.jpg"),
                        List.of("wifi", "no pet", "no smoke")
                ),
                new DestinationOnSearchDto(
                        UUID.randomUUID(),
                        "paddle",
                        "paddle sur la meuse avec un guide super sympa spécialisé en paddle et en bière",
                        50.46036,
                        5.4993,
                        "avenue de la meuse, 37 4000 Liege",
                        "ACTIVITY",
                        List.of("https://ecopark-adventures.com/wp-content/uploads/2019/07/HP-Main-picture-Tournai-e1580480117562.jpg"),
                        List.of("no pet", "no smoke")
                ),
                new DestinationOnSearchDto(
                        UUID.randomUUID(),
                        "EcoHotel",
                        "nous vous accueillons dans notre hotel 5 étoiles très éco avec des chambres en bois et des repas bio",
                        51.0336,
                        5.0273,
                        "rue de l'hotel, 25 4671 Saive",
                        "LODGING",
                        List.of("https://casaeconstrucao.org/wp-content/uploads/2020/03/casas-baratas-tiny-house-no-jardim.jpg"),
                        List.of("wifi", "no pet", "no smoke")
                )
        );

        List<DestinationOnSearchDto> TEMPDestination = null;

        //recherche TEMPORAIRE:

        if(Objects.equals(searchCriteria.getType(), "LODGING")){
            TEMPDestination = TEMPDestinationAll.stream().filter(destination -> destination.getType().equals("LODGING")).toList();
        }else if(Objects.equals(searchCriteria.getType(), "ACTIVITY")){
            TEMPDestination = TEMPDestinationAll.stream().filter(destination -> destination.getType().equals("ACTIVITY")).toList();
        }else{
            TEMPDestination = TEMPDestinationAll;
        }
        if(searchCriteria.getTags() != null){
            for(String tag : searchCriteria.getTags()){
                TEMPDestination = TEMPDestination.stream().filter(destination -> destination.getTags().contains(tag)).toList();
            }
        }

        return TEMPDestination;
    }

    public Destination getDestinationById(UUID id){
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

    public List<String> getDestinationTypes() {
        return destinationTypeRepo.getAllTypes();
    }
}
