package be.ecotravel.ecotravelback.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String country;

    private String location;

    private String street;

    private int number;

    private int postalCode;

    private long longitude;

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

    //region Get Functions
    public UUID getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getLocation() {
        return location;
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public int getPostalCode() {
        return postalCode;
    }
    //endregion

    //region Set Functions
    public void setCountry(String country) {
        this.country = country;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
    //endregion
}
