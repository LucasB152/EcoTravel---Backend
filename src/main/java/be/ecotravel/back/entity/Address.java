package be.ecotravel.back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Setter
    private String country;

    @Setter
    private String location;

    @Setter
    private String street;

    @Setter
    private int number;

    @Setter
    private int postalCode;

    @Setter
    private long longitude;

    @Setter
    private long latitude;

    protected Address() {}

    public Address(
            UUID id,
            String country,
            String location,
            String street,
            int number,
            int postalCode,
            long longitude,
            long latitude
    ) {
        this.id = id;
        this.country = country;
        this.location = location;
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
        this.longitude = longitude;
        this.latitude = latitude;
    }

}
