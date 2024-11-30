package be.ecotravel.back.service;

import be.ecotravel.back.destination.dto.DestinationResponseDto;
import be.ecotravel.back.destination.mapper.DestinationMapper;
import be.ecotravel.back.entity.Destination;
import be.ecotravel.back.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Destination> getPopular() {
        return this.destinationRepo.findAll();
    }

    public Destination getDestinationById(UUID id){
        Destination destination = destinationRepo.findById(id).orElse(null);

        return destination;
    }

    //TODO Retirer ça là
    private UUID getNewId() {
        return UUID.randomUUID();
    }
}
