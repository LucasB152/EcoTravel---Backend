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

    private String zipcode;

    private double longitude;

    private double latitude;

    public Address() {}

    @Override
    public String toString() {
        return street + " " + number + ", " + zipcode + " " + location + ", " + country;
    }
}
