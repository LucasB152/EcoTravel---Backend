package be.ecotravel.back.user.dto;

import java.util.UUID;

public record UserRoleDto(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String role
) {
}
