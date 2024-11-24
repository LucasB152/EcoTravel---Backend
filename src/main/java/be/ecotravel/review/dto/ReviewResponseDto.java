package be.ecotravel.review.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReviewResponseDto(
        UUID id,
        int score,
        String comment,
        boolean edited,
        String username,
        String dateString
) {}
