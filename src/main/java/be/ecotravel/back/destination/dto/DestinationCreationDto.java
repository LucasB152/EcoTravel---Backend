package be.ecotravel.back.destination.dto;

public record DestinationCreationDto(
    String name,
    String description,
    String price,
    String capacity,
    String contactPhone,
    String contactEmail,
    String destinationType,
    String country,
    String location,
    String street,
    String number,
    String zipcode,
    String[] tagsId
) {
}
