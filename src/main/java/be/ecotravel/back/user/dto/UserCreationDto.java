package be.ecotravel.back.user.dto;

public record UserCreationDto(
        String email,
        String firstname,
        String lastname,
        String password
) {
}