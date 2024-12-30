package be.ecotravel.back.review.dto;

import java.util.UUID;

public record ReviewEditDto(
        UUID id,
        int score,
        String title,
        String comment,
        UUID userId
) {
}
