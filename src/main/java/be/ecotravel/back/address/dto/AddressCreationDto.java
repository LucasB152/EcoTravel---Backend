package be.ecotravel.back.address.dto;

public record AddressCreationDto(
    String country,
    String location,
    String street,
    String number,
    String postalCode,
    long longitude,
    long latitude
) {
}
