package be.ecotravel.ecotravelback.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Destination {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    private String name;

    private String description;

    private String price;

    private String capacity;

    private boolean isVisible;

    private String contactPhone;

    private String contactEmail;

    private String imageFolderPath;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DestinationType destinationType;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Address address;

    protected Destination() {}

    //TODO retirer le url image du constructeur
    public Destination(UUID id, String name, String description, String urlImage) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    //region Get Functions
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    //endregion

    //region Set Functions
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //endregion
}
