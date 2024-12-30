package be.ecotravel.back.review.dto;

import java.util.UUID;

public record ReviewDeleteDto(
        UUID reviewId,
        UUID userId
) {
}
