package be.ecotravel.back.user.dto;

public record UserLoginDto(
        String email,
        String password
) {
}
