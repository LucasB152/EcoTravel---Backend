package be.ecotravel.report.mapper;

import be.ecotravel.entity.Destination;
import be.ecotravel.entity.Report;
import be.ecotravel.entity.User;
import be.ecotravel.report.dto.ReportCreationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "edited", ignore = true)
    Report toEntity(ReportCreationDto reportDto, User user, Destination destination);

}
