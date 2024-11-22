package be.ecotravel.ecotravelback.service;

import be.ecotravel.ecotravelback.dto.DestinationDto;
import be.ecotravel.ecotravelback.dto.ReportDto;
import be.ecotravel.ecotravelback.dto.UserDto;
import be.ecotravel.ecotravelback.entity.Destination;
import be.ecotravel.ecotravelback.entity.Report;
import be.ecotravel.ecotravelback.entity.User;

public class Mapper {

    public static User userDtoToUser(UserDto userDto) {
        return null;
    }

    public static UserDto userToUserDto(User user) {
        return null;
    }

    public static Destination destinationDtoToDestination(DestinationDto destinationDto) {
        return null;
    }

    public static DestinationDto destinationToDestinationDto(Destination destination) {
        return null;
    }

    public static Report reportDtoToReport(ReportDto reportDto, User user, Destination destination) {
        return new Report(
                reportDto.id(),
                reportDto.text(),
                reportDto.isEdited(),
                reportDto.date(),
                user,
                destination
        );
    }

    public static ReportDto reportToReportDto(Report report, UserDto userDto, DestinationDto destinationDto) {
        return new ReportDto(
                report.getId(),
                report.getText(),
                report.isEdited(),
                report.getDate(),
                userDto,
                destinationDto
        );
    }

}
