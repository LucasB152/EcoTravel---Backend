package be.ecotravel.back.request.dto;

import java.util.UUID;

public record RequestPutDto(
        String status,
        String message
) {
}
