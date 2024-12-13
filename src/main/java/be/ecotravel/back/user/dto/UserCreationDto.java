package be.ecotravel.back.user.dto;

public record UserCreationDto(
        String email,
        String firstName,
        String lastName,
        String password
) {
}