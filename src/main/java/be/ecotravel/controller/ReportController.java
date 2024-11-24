package be.ecotravel.controller;

import be.ecotravel.report.dto.ReportCreationDto;
import be.ecotravel.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/create-report")
    @ResponseStatus(HttpStatus.CREATED)
    public void createReport(@RequestBody ReportCreationDto reportCreationDto) {
        reportService.createReport(reportCreationDto);
    }

}
