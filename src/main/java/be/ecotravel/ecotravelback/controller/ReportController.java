package be.ecotravel.ecotravelback.controller;

import be.ecotravel.ecotravelback.entity.Report;
import be.ecotravel.ecotravelback.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/post-report")
    public void postReport(@RequestBody Report report) {

    }



}
