package be.ecotravel.back.service;

import be.ecotravel.back.entity.Destination;
import be.ecotravel.back.entity.Itinerary;
import be.ecotravel.back.entity.User;
import be.ecotravel.back.itinerary.dto.ItineraryCreationDto;
import be.ecotravel.back.itinerary.dto.ItineraryListResponseDto;
import be.ecotravel.back.itinerary.mapper.ItineraryMapper;
import be.ecotravel.back.repository.DestinationRepository;
import be.ecotravel.back.repository.ItineraryRepository;
import be.ecotravel.back.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItineraryService {

    private final ItineraryRepository itineraryRepository;
    private final ItineraryMapper itineraryMapper;
    private final DestinationRepository destinationRepository;
    private final UserRepository userRepository;
    private final StepService stepService;

    @Autowired
    public ItineraryService(
            ItineraryRepository itineraryRepository,
            ItineraryMapper itineraryMapper,
            DestinationRepository destinationRepository,
            UserRepository userRepository,
            StepService stepService
    ) {
        this.itineraryRepository = itineraryRepository;
        this.itineraryMapper = itineraryMapper;
        this.destinationRepository = destinationRepository;
        this.userRepository = userRepository;
        this.stepService = stepService;
    }

    public void createItinerary(ItineraryCreationDto itineraryDto) {
        User user = userRepository.findUserById(itineraryDto.userId())
                .orElseThrow(EntityExistsException::new);

        Destination startDestination = destinationRepository.findById(itineraryDto.firstDestination())
                .orElseThrow(() -> new EntityExistsException("Start Destination Not Found"));

        Itinerary itinerary = itineraryMapper.toEntity(itineraryDto, user);

        itineraryRepository.save(itinerary);

        stepService.createStep(1, startDestination, itinerary);
    }

    public List<ItineraryListResponseDto> getItineraryFromUser(UUID userId){
        User user = userRepository.findUserById(userId)
                .orElseThrow(EntityExistsException::new);

        return itineraryRepository.findByOwnerUser(user).stream().map(itineraryMapper::toItineraryList).toList();
    }

    private double calculateCarbonFootprint(Itinerary itinerary) {
        double emissionFactor;

        return 0;
    }
}
