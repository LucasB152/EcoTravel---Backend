package be.ecotravel.back.destination.dto;

import java.util.List;
import java.util.UUID;
public record DestinationDetailsDto(UUID destinationID,
                                    String name,
                                    String description,
                                    String price,
                                    String capacity,
                                    String contactPhone,
                                    String contactEmail,
                                    List<String> images,
                                    String destinationType,
                                    String address,
                                    List<String> tags,
                                    boolean isVisible


) {
}
