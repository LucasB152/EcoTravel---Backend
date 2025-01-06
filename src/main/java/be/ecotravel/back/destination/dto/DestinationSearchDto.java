package be.ecotravel.back.destination.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
public class DestinationSearchDto {
    private final UUID destinationID;
    private final String name;
    private final String description;
    @Setter
    private List<String> images;
    private final double longitude;
    private final double latitude;

    public DestinationSearchDto(UUID destinationID, String name, String description, double longitude, double latitude) {
        this.destinationID = destinationID;
        this.name = name;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
    }

}