package be.ecotravel.back.user.dto;

public record UserPasswordModificationDto (
        String userId,
        String currentPassword,
        String newPassword
){}
