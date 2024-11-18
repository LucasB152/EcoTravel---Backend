package be.ecotravel.ecotravelback.controller;

import be.ecotravel.ecotravelback.entity.Destination;
import be.ecotravel.ecotravelback.service.DestinationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DestinationController {

    @Autowired
    DestinationService destinationService;
    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/popular-destination")
    public Destination[] destinations() {
        return destinationService.getPopular();
    }

    @GetMapping("/destination/{id}")
    public Destination getDestinationById(@PathVariable UUID id) {
        return destinationService.getDestinationById(id);
    }
}
