package be.ecotravel.back.step.dto;

import be.ecotravel.back.destination.dto.DestinationResponseDto;

import java.util.UUID;

public record StepResponse(
        UUID id,
        int orderSequence,
        DestinationResponseDto destination
) {

}
