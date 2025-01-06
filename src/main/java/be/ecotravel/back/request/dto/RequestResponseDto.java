package be.ecotravel.back.request.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record RequestResponseDto(
    UUID id,
    String userFullName,
    String email,
    String contactPhone,
    String motivation,
    String company,
    String identifier,
    String websiteUrl,
    String[] services,
    String description,
    String[] files,
    String hostStatus
) {
}
