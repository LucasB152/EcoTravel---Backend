package be.ecotravel.back.tag.dto;

import java.util.UUID;

public record TagResponseDto(
        UUID id,
        String name
) {
}
