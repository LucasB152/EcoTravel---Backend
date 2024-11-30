package be.ecotravel.back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Entity
public class Destination {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @Setter
    private String name;

    @Setter
    private String description;

    @Setter
    private String price;

    @Setter
    private String capacity;

    @Setter
    private boolean isVisible;

    @Setter
    private String contactPhone;

    @Setter
    private String contactEmail;

    @Setter
    private String imageFolderPath;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private DestinationType destinationType;

    @ManyToOne(optional = false)
    private Address address;

    protected Destination() {}

    //TODO retirer le url image du constructeur
    public Destination(UUID id, String name, String description, String urlImage) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
