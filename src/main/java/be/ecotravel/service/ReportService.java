package be.ecotravel.service;

import be.ecotravel.destination.dto.DestinationDto;
import be.ecotravel.entity.Destination;
import be.ecotravel.entity.Report;
import be.ecotravel.entity.User;
import be.ecotravel.report.dto.ReportCreationDto;
import be.ecotravel.report.dto.ReportResponseDto;
import be.ecotravel.report.mapper.ReportMapper;
import be.ecotravel.repository.DestinationRepository;
import be.ecotravel.repository.ReportRepository;
import be.ecotravel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepo;
    private final UserRepository userRepo;
    private final DestinationRepository destinationRepo;

    private final ReportMapper reportMapper;

    @Autowired
    public ReportService(ReportRepository reportRepo, UserRepository userRepo, DestinationRepository destinationRepo, ReportMapper reportMapper) {
        this.reportRepo = reportRepo;
        this.userRepo = userRepo;
        this.destinationRepo = destinationRepo;
        this.reportMapper = reportMapper;
    }

    public void createReport(ReportCreationDto reportCreationDto) {
        User user = userRepo.getReferenceById(reportCreationDto.userId()); //Au vu de la doc ça peut peut etre lancer une exception sur le premier appel même s'il est existant, on verra qunad ça fonctionnera si je dois fix
        Destination destination = destinationRepo.getReferenceById(reportCreationDto.destinationId());

        Report report = reportMapper.toEntity(reportCreationDto, user, destination);
        reportRepo.save(report);
    }

    public List<ReportResponseDto> getReportsOfDestination(DestinationDto destinationDto) throws Exception {
        throw new Exception(); //TODO
    }

}
