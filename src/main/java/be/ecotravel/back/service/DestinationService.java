package be.ecotravel.back.service;

import be.ecotravel.back.destination.dto.DestinationCreationDto;
import be.ecotravel.back.destination.dto.DestinationResponseDto;
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
}
