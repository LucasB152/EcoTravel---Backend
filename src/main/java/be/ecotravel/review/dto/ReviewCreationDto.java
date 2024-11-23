package be.ecotravel.review.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReviewCreationDto(
    UUID id,
    int score,
    String comment,
    LocalDateTime createdAt,
    boolean isEdited,
    UUID userId,
    UUID destinationId
) {
}
