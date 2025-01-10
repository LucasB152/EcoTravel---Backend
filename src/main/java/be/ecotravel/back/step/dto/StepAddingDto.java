package be.ecotravel.back.step.dto;

import java.util.UUID;

public record StepAddingDto(
        UUID destinationId,
        UUID itineraryId
) {
}
