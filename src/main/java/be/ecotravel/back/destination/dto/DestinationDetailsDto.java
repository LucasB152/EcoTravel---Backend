package be.ecotravel.back.destination.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
/**
 * DTO for destination details.
 * !!!!!la liste des images et la liste des tags peuvent changer et devenir juste des ID!!!!!
 */
@Getter
public class DestinationDetailsDto {
    private final UUID destinationID;
    private final String name;
    private final String description;
    private final String price;
    private final String capacity;
    private final String contactPhone;
    private final String contactEmail;
    @Setter
    private List<String> images;
    private final String destinationType;
    private final String address;
    @Setter
    private List<String> tags;
    private final boolean isVisible;
    private final double latitude;
    private final double longitude;

    public DestinationDetailsDto(UUID destinationID, String name, String description, String price,
                                 String capacity, String contactPhone, String contactEmail,
                                 List<String> images, String destinationType, String address,
                                 List<String> tags, boolean isVisible, double latitude, double longitude) {
        this.destinationID = destinationID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.capacity = capacity;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.images = images;
        this.destinationType = destinationType;
        this.address = address;
        this.tags = tags;
        this.isVisible = isVisible;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
