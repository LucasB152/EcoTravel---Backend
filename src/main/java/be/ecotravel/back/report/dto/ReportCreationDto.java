package be.ecotravel.back.report.dto;

import java.util.UUID;

public record ReportCreationDto(
        String text,
        UUID userId,
        UUID destinationId
){
}