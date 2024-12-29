package be.ecotravel.back.address.dto;

public record AddressCreationDto(
    String country,
    String location,
    String street,
    String number,
    String zipcode,
    double longitude,
    double latitude
) {
}
