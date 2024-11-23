package be.ecotravel.report.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReportCreationDto(
        String text,
        UUID userId,
        UUID destinationId
){
}