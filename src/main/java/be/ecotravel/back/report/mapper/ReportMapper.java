package be.ecotravel.back.report.mapper;

import be.ecotravel.back.entity.Destination;
import be.ecotravel.back.entity.Report;
import be.ecotravel.back.entity.User;
import be.ecotravel.back.report.dto.ReportCreationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "edited", ignore = true)
    Report toEntity(ReportCreationDto reportDto, User user, Destination destination);

}
