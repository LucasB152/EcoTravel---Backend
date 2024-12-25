package be.ecotravel.back.user.dto;

public record UserResponse(
        String firstName,
        String lastName,
        String email,
        String profilePicturePath
) {
}
