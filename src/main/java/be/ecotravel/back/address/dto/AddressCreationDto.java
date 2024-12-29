package be.ecotravel.back.address.dto;

public record AddressCreationDto(
    String country,
    String location,
    String street,
    String number,
    String postalCode,
    double longitude,
    double latitude
) {
}
