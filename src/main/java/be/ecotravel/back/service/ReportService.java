package be.ecotravel.back.service;

import be.ecotravel.back.destination.dto.DestinationDto;
import be.ecotravel.back.entity.Destination;
import be.ecotravel.back.entity.Report;
import be.ecotravel.back.entity.User;
import be.ecotravel.back.report.dto.ReportCreationDto;
import be.ecotravel.back.report.dto.ReportResponseDto;
import be.ecotravel.back.report.mapper.ReportMapper;
import be.ecotravel.back.repository.DestinationRepository;
import be.ecotravel.back.repository.ReportRepository;
import be.ecotravel.back.repository.UserRepository;
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
