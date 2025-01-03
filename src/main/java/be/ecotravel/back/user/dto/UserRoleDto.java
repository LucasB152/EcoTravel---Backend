package be.ecotravel.back.user.dto;

public record UserRoleDto(
        String firstName,
        String lastName,
        String email,
        String role
) {
}
