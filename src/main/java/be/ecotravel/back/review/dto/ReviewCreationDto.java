package be.ecotravel.back.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ReviewCreationDto(
    @Min(0) @Max(5)
    int score,

    String comment,

    @NotNull
    UUID userId,

    @NotNull
    UUID destinationId
) {
}
