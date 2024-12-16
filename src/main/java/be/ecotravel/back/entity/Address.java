package be.ecotravel.back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String country;

    private String location;

    private String street;

    private String number;

    private String postalCode;

    private double longitude;

    private double latitude;

    protected Address() {}

    public Address(
            String country,
            String location,
            String street,
            String number,
            String postalCode,
            double longitude,
            double latitude
    ) {
        this.country = country;
        this.location = location;
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
        this.longitude = longitude;
        this.latitude = latitude;
    }

}
