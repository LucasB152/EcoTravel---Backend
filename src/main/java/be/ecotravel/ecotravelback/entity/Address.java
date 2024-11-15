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

    protected Address() {}

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
