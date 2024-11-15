package be.ecotravel.ecotravelback.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String path;

    @ManyToOne(optional = false)
    private Destination destination;

    protected Image() {}

    public Image(Destination destination, String path) {
        this.destination = destination;
        this.path = path;
    }

    public UUID getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public Destination getDestination() {
        return destination;
    }
}
