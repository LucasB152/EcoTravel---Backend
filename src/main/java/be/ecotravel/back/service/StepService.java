package be.ecotravel.back.service;

import be.ecotravel.back.entity.Destination;
import be.ecotravel.back.entity.Itinerary;
import be.ecotravel.back.entity.Step;
import be.ecotravel.back.repository.DestinationRepository;
import be.ecotravel.back.repository.ItineraryRepository;
import be.ecotravel.back.repository.StepRepository;
import be.ecotravel.back.step.dto.StepAddingDto;
import be.ecotravel.back.step.dto.StepResponse;
import be.ecotravel.back.step.mapper.StepMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<StepResponse> getStepsFromItinerary(UUID itineraryId) {
        return stepRepository.findByItineraryIdOrderByOrderSequenceAsc(itineraryId).stream().map(stepMapper::toStepResponse).toList();
    }

    public void deleteStepFromItinerary(UUID stepId, UUID itineraryId) {
        Step step = stepRepository.findById(stepId)
                .orElseThrow(EntityNotFoundException::new);

        int maxOrder = stepRepository.findMaxOrderSequenceByItineraryId(itineraryId);

        for(int i = step.getOrderSequence()+1; i <= maxOrder; i++){
            Step stepToModify = stepRepository.findByItineraryIdAndOrderSequence(itineraryId, i);
            stepToModify.setOrderSequence(i - 1);
            stepRepository.save(stepToModify);
        }

        stepRepository.delete(step);
    }

    public void putStepUp(UUID stepId, UUID itineraryId) {
        Step step = stepRepository.findById(stepId)
                .orElseThrow(EntityNotFoundException::new);

        int actualOrder = step.getOrderSequence();
        if (actualOrder > 1) {
            Step stepUp = stepRepository.findByItineraryIdAndOrderSequence(itineraryId, actualOrder - 1);
            step.setOrderSequence(actualOrder - 1);
            stepUp.setOrderSequence(actualOrder);

            stepRepository.save(step);
            stepRepository.save(stepUp);
        }
    }

    public void putStepDown(UUID stepId, UUID itineraryId) {
        Step step = stepRepository.findById(stepId)
                .orElseThrow(EntityNotFoundException::new);

        int maxOrder = stepRepository.findMaxOrderSequenceByItineraryId(itineraryId);

        int actualOrder = step.getOrderSequence();
        if(actualOrder < maxOrder){
            Step stepDown = stepRepository.findByItineraryIdAndOrderSequence(itineraryId, actualOrder + 1);
            stepDown.setOrderSequence(actualOrder);
            step.setOrderSequence(actualOrder + 1);

            stepRepository.save(step);
            stepRepository.save(stepDown);
        }
    }
}
