package be.ecotravel.back.destination.dto;

import be.ecotravel.back.address.dto.AddressCreationDto;

import java.util.UUID;

public record DestinationCreationDto(
    String name,
    String description,
    String price,
    String capacity,
    boolean visible,
    String contactPhone,
    String contactEmail,
    String destinationType,
    UUID userId,
    AddressCreationDto addressCreationDto
) {
}
