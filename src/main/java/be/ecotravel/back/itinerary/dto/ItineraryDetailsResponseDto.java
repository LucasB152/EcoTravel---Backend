package be.ecotravel.back.itinerary.dto;

import be.ecotravel.back.step.dto.StepResponse;

import java.util.List;
import java.util.UUID;

public record ItineraryDetailsResponseDto(
    UUID id,
    String title,
    List<StepResponse> steps

) {
}
