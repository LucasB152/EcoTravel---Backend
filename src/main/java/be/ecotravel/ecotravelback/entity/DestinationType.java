package be.ecotravel.ecotravelback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class DestinationType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    protected DestinationType() {}

    public DestinationType(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    //Get
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //Set
    public void setName(String name) {
        this.name = name;
    }

}
