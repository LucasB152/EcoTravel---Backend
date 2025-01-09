package be.ecotravel.back.service;

import be.ecotravel.back.entity.Destination;
import be.ecotravel.back.entity.Itinerary;
import be.ecotravel.back.entity.Step;
import be.ecotravel.back.repository.DestinationRepository;
import be.ecotravel.back.repository.ItineraryRepository;
import be.ecotravel.back.repository.StepRepository;
import be.ecotravel.back.step.dto.StepAddingDto;
import be.ecotravel.back.step.mapper.StepMapper;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StepService {
    private final StepRepository stepRepository;
    private final StepMapper stepMapper;
    private final ItineraryRepository itineraryRepository;
    private final DestinationRepository destinationRepository;

    @Autowired
    public StepService(StepRepository stepRepository,
                       StepMapper stepMapper,
                       ItineraryRepository itineraryRepository,
                       DestinationRepository destinationRepository
    ) {
        this.stepRepository = stepRepository;
        this.stepMapper = stepMapper;
        this.itineraryRepository = itineraryRepository;
        this.destinationRepository = destinationRepository;
    }

    public Step createStep(int orderSequence, Destination startDestination, Itinerary itinerary) {
        Step step = stepMapper.toEntity(orderSequence, startDestination, itinerary);
        stepRepository.save(step);
        return step;
    }

    public Integer getMaxOrderSequence(UUID itineraryId) {
        return stepRepository.findMaxOrderSequenceByItineraryId(itineraryId);
    }

    public void addStepToItinerary(StepAddingDto stepAddingDto) {

        Destination destination = destinationRepository.findById(stepAddingDto.destinationId())
                .orElseThrow(() -> new EntityExistsException("Start Destination Not Found"));

        Itinerary itinerary = itineraryRepository.findById(stepAddingDto.itineraryId())
                .orElseThrow(() -> new EntityExistsException("Itinerary not found"));

        int nextOrderSequence = getMaxOrderSequence(stepAddingDto.itineraryId()) + 1;

        Step step = stepMapper.toEntity(nextOrderSequence, destination, itinerary);

        stepRepository.save(step);
    }
}
