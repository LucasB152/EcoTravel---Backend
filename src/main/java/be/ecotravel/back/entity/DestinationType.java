package be.ecotravel.back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class DestinationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private DestinationTypeEnum type;

    protected DestinationType() {}

    public DestinationType(DestinationTypeEnum type) {
        this.type = type;
    }

}
