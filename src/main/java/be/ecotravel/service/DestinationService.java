package be.ecotravel.service;

import be.ecotravel.destination.dto.DestinationDto;
import be.ecotravel.destination.dto.DestinationResponseDto;
import be.ecotravel.destination.mapper.DestinationMapper;
import be.ecotravel.entity.Destination;
import be.ecotravel.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DestinationService {

    private final DestinationMapper destinationMapper; //Si ne fonctionne pas, mettre en protected

    private final DestinationRepository destinationRepo;

    @Autowired
    public DestinationService(DestinationMapper destinationMapper, DestinationRepository destinationRepo) {
        this.destinationMapper = destinationMapper;
        this.destinationRepo = destinationRepo;
    }

    public void getPopular() {
        //TODO
    }

    public DestinationResponseDto getDestinationById(UUID id){
        Destination destination = destinationRepo.findById(id).orElse(null);

        return destinationMapper.toResponseDto(destination);
    }

    //TODO Retirer ça là
    private UUID getNewId() {
        return UUID.randomUUID();
    }
}
