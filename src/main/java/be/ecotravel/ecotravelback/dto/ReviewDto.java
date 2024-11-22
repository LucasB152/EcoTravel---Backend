package be.ecotravel.ecotravelback.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReviewDto(
    UUID id,
    int score,
    String comment,
    LocalDateTime createdAt,
    boolean isEdited,
    UUID userId,
    UUID destinationId
) {
}
