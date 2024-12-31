package be.ecotravel.back.destination.dto;

import java.util.UUID;

public record DestinationSearchDto(UUID destinationID,
                                   String name,
                                   String description,
                                   String[] images,
                                   double longitude,
                                   double latitude
) {
}