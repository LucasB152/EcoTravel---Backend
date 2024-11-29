package be.ecotravel.back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Entity
public class Step implements Comparable<Step> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Setter
    private int orderSequence;

    @ManyToOne(optional = false)
    private Destination destination;

    @ManyToOne(optional = false)
    private Itinerary itinerary;

    protected Step() {}

    @Override
    public int compareTo(Step other) {
        return Integer.compare(this.orderSequence, other.orderSequence);
    }
}
