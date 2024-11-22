package be.ecotravel.ecotravelback.service;

import be.ecotravel.ecotravelback.dto.DestinationDto;
import be.ecotravel.ecotravelback.dto.UserDto;
import be.ecotravel.ecotravelback.entity.Destination;
import be.ecotravel.ecotravelback.entity.Report;
import be.ecotravel.ecotravelback.entity.User;
import be.ecotravel.ecotravelback.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepo;

    public void createReport(String text, UserDto userDto, DestinationDto destinationDto) {
        User user = Mapper.userDtoToUser(userDto);
        Destination destination = Mapper.destinationDtoToDestination(destinationDto);

        Report report = new Report(UUID.randomUUID(), text, false, LocalDateTime.now(), user, destination);
        reportRepo.save(report);
    }

    public List<Report> getReportsOfDestination(DestinationDto destinationDto) throws Exception {
        throw new Exception(); //TODO
    }

}
