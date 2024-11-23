package be.ecotravel.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Step implements Comparable<Step> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int orderSequence;

    @ManyToOne(optional = false)
    private Destination destination;

    @ManyToOne(optional = false)
    private Itinerary itinerary;

    protected Step() {}

    //region Get Functions
    public UUID getId() {
        return id;
    }

    public int getOrderSequence() {
        return orderSequence;
    }

    public Destination getDestination() {
        return destination;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }
    //endregion

    public void setOrderSequence(int orderSequence) {
        this.orderSequence = orderSequence;
    }

    @Override
    public int compareTo(Step other) {
        return Integer.compare(this.orderSequence, other.orderSequence);
    }
}
