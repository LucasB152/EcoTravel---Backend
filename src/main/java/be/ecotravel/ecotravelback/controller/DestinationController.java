package be.ecotravel.ecotravelback.controller;

import be.ecotravel.ecotravelback.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DestinationController {

    @Autowired
    DestinationService destinationService;

    @GetMapping("/most-popular")
    public ResponseEntity<?> destinations() {
        return destinationService.getPopular(6);
    }

}
