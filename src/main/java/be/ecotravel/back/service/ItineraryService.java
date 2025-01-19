package be.ecotravel.back.service;

import be.ecotravel.back.entity.Destination;
import be.ecotravel.back.entity.Itinerary;
import be.ecotravel.back.entity.User;
import be.ecotravel.back.itinerary.dto.ItineraryCreationDto;
import be.ecotravel.back.itinerary.dto.ItineraryResponseDto;
import be.ecotravel.back.itinerary.mapper.ItineraryMapper;
import be.ecotravel.back.repository.DestinationRepository;
import be.ecotravel.back.repository.ItineraryRepository;
import be.ecotravel.back.repository.UserRepository;
import be.ecotravel.back.step.dto.StepResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
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
                .orElseThrow(EntityNotFoundException::new);

        Destination startDestination = destinationRepository.findById(itineraryDto.firstDestination())
                .orElseThrow(() -> new EntityExistsException("Start Destination Not Found"));

        Itinerary itinerary = itineraryMapper.toEntity(itineraryDto, user, 0);

        itineraryRepository.save(itinerary);

        stepService.createStep(1, startDestination, itinerary);
    }

    public List<ItineraryResponseDto> getItineraryFromUser(UUID userId) {
        User user = userRepository.findUserById(userId)
                .orElseThrow(EntityNotFoundException::new);


        return itineraryRepository.findByOwnerUser(user).stream().map(itinerary -> {
            List<StepResponse> steps = stepService.getStepsFromItinerary(itinerary.getId());
            return itineraryMapper.toItineraryResponse(itinerary, steps);
        }).toList();
    }

    public ItineraryResponseDto getItinerary(UUID id) {
        Itinerary itinerary = itineraryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        List<StepResponse> steps = stepService.getStepsFromItinerary(id);

        return itineraryMapper.toItineraryResponse(itinerary, steps);
    }

    public void deleteItinerary(UUID id) {
        Itinerary itinerary = itineraryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        this.itineraryRepository.delete(itinerary);
    }

    public void updateItineraryName(UUID itineraryId, String newTitle) {
        Itinerary itinerary = itineraryRepository.findById(itineraryId)
                .orElseThrow(EntityNotFoundException::new);

        itinerary.setTitle(newTitle);

        itineraryRepository.save(itinerary);
    }
}
