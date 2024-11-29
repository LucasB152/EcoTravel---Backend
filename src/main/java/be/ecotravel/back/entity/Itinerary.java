package be.ecotravel.back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Entity
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Setter
    private String name;

    @ManyToOne(optional = false)
    private User user;

    @Setter
    @ManyToOne(optional = false)
    private Destination startDestination;

    @Setter
    @ManyToOne(optional = false)
    private Destination endDestination;

    protected Itinerary() {}

}
