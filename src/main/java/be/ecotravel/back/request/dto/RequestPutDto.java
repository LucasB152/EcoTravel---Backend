package be.ecotravel.back.request.dto;

import java.util.UUID;

public record RequestPutDto(
        UUID id,
        String status
) {
}
