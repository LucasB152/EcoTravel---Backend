package be.ecotravel.back.destination.dto;

import be.ecotravel.back.entity.Address;
import be.ecotravel.back.entity.DestinationType;

import java.util.UUID;

public record DestinationResponseDto(
        UUID id,
        String name,
        DestinationType destinationType,
        Address address
) {
}
