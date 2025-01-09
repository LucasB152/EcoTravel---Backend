package be.ecotravel.back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    @ManyToOne(optional = false)
    private User ownerUser;

//    @ManyToMany()
//    private Set<User> sharedUsers;

    @ManyToOne(optional = false)
    private Destination startDestination;

    @ManyToOne(optional = true)
    private Destination endDestination;

    private double distance;

    public Itinerary() {

    }

}
