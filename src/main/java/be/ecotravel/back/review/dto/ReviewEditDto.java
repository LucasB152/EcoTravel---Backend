package be.ecotravel.back.review.dto;

import java.util.UUID;

public record ReviewEditDto(
        UUID id,
        int score,
        String comment
) {
}