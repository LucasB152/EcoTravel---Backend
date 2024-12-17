package be.ecotravel.back.request.dto;

import java.util.UUID;

public record RequestResponseDto(
    UUID id,
    String text,
    String status,
    String userFullName
) {
}
