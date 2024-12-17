package be.ecotravel.back.user.dto;

public record UserResponse(
        String firstname,
        String lastname,
        String email,
        String profilePicturePath
) {
}
