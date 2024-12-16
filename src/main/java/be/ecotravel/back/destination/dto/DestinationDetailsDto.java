package be.ecotravel.back.destination.dto;

import java.util.List;
import java.util.UUID;

public record DestinationDetailsDto(UUID id,
                                    String name,
                                    String description,
                                    String price,
                                    String contactPhone,
                                    String contactEmail,
                                    List<String> imagesPath) {
}
