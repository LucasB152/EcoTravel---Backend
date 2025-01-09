package be.ecotravel.back.itinerary.dto;

import java.util.UUID;

public record ItineraryListResponseDto(
        UUID id,
        String title
) {
}
