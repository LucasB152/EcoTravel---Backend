package be.ecotravel.back.destination.dto;

import java.util.List;
import java.util.UUID;

/**
 * DTO for destination details.
 * !!!!!la liste des images et la liste des tags peuvent changer et devenir juste des ID!!!!!
 * @param destinationID
 * @param name
 * @param description
 * @param price
 * @param capacity
 * @param contactPhone
 * @param contactEmail
 * @param images
 * @param destinationType
 * @param address
 * @param tags
 * @param isVisible
 */
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
