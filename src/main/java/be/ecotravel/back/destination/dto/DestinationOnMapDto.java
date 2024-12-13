package be.ecotravel.back.destination.dto;


import lombok.Setter;

import java.util.List;
import java.util.UUID;


/**
 * DTO pour destination sur la map (pour la page d'accueil)
 */
public class DestinationOnMapDto {
    @Setter
    private UUID destinationID;
    @Setter
    private double lat;
    @Setter
    private double lng;
    @Setter
    private String name;
    @Setter
    private String destinationTypeName;
    @Setter
    private List<String> images;


    public DestinationOnMapDto(UUID destinationID, double lat, double lng, String name, String destinationTypeName, List<String> images) {
        this.destinationID = destinationID;
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.destinationTypeName = destinationTypeName;
        this.images = images;
    }

    // Getters and setters
    public UUID getDestinationID() {
        return destinationID;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getName() {
        return name;
    }

    public String getDestinationTypeName() {
        return destinationTypeName;
    }

    public List<String> getImages() {
        return images;
    }

}