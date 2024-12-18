package be.ecotravel.back.destination.dto;


import java.util.List;
import java.util.UUID;


/**
 * dto de destination pour la recherche et qui contient **TOUTES** les informations d'une destination
 * !!!!! ATTENTION version temporaire et a completer (type, tags et images peuvent changer)!!!!!
 */
public class DestinationOnSearchDto {
    private UUID destinationID;
    private String name;
    private String description;
    private double lat;
    private double lng;
    private String address;
    private String type;
    private List<String> images;
    private List<String> tags;

    public DestinationOnSearchDto(UUID destinationID, String name, String description, double lat, double lng, String address, String type, List<String> images, List<String> tags) {
        this.destinationID = destinationID;
        this.name = name;
        this.description = description;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
        this.type = type;
        this.images = images;
        this.tags = tags;
    }

    public UUID getDestinationID() {
        return destinationID;
    }

    public void setDestinationID(UUID destinationID) {
        this.destinationID = destinationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}