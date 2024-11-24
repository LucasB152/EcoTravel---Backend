package be.ecotravel.back.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Destination startDestination;

    @ManyToOne(optional = false)
    private Destination endDestination;

    protected Itinerary() {}

    //region Get Functions
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public Destination getStartDestination() {
        return startDestination;
    }

    public Destination getEndDestination() {
        return endDestination;
    }
    //endregion

    //region Set Functions
    public void setName(String name) {
        this.name = name;
    }

    public void setStartDestination(Destination startDestination) {
        this.startDestination = startDestination;
    }

    public void setEndDestination(Destination endDestination) {
        this.endDestination = endDestination;
    }
    //endregion

}
