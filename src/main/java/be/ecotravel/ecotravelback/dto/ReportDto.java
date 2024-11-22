package be.ecotravel.ecotravelback.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReportDto(
        UUID id,
        String text,
        boolean isEdited,
        LocalDateTime date,
        UserDto userDto,
        DestinationDto destinationDto
){

}