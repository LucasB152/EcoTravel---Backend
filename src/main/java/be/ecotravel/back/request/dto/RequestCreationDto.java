package be.ecotravel.back.request.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record RequestCreationDto(
        String contactEmail,
        String contactPhone,
        String motivation,
        String company,
        String identifier,
        String website,
        String[] services,
        String description,
        MultipartFile[] files,
        String status,
        UUID userId
) {
}
