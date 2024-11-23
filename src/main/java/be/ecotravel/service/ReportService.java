package be.ecotravel.service;

import be.ecotravel.dto.DestinationDto;
import be.ecotravel.dto.UserDto;
import be.ecotravel.entity.Destination;
import be.ecotravel.entity.Report;
import be.ecotravel.entity.User;
import be.ecotravel.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReportService {

    private final ReportRepository reportRepo;

    @Autowired
    public ReportService(ReportRepository reportRepo) {
        this.reportRepo = reportRepo;
    }

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
