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
    private String number;

    @Setter
    private String postalCode;

    @Setter
    private long longitude;

    @Setter
    private long latitude;

    protected Address() {}

    public Address(
            String country,
            String location,
            String street,
            String number,
            String postalCode,
            long longitude,
            long latitude
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
