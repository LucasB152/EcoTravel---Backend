package be.ecotravel.back.itinerary.dto;

import java.util.UUID;

public record ItineraryCreationDto(
        UUID userId,
        String title,
        UUID firstDestination
) {
}
