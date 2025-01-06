package be.ecotravel.back.review.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReviewResponseDto(
        UUID id,
        int score,
        String title,
        String comment,
        boolean edited,
        UUID userId,
        String username,
        String dateStringCreation,
        String dateStringModification
) {}
