package be.ecotravel.report.dto;

import java.util.UUID;

public record ReportCreationDto(
        String text,
        UUID userId,
        UUID destinationId
){
}