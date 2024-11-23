package be.ecotravel.report.mapper;

import be.ecotravel.entity.Report;
import be.ecotravel.report.dto.ReportCreationDto;
import org.mapstruct.Mapper;

@Mapper
public interface ReportMapper {

    Report toEntity(ReportCreationDto reportDto);

}
