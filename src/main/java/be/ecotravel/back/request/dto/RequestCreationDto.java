package be.ecotravel.back.request.dto;

import java.util.UUID;

public record RequestCreationDto(
        UUID userId,
        String text
) {
}
