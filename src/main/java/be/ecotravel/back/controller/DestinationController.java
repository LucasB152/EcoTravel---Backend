package be.ecotravel.back.controller;

import be.ecotravel.back.destination.dto.DestinationCreationDto;
import be.ecotravel.back.destination.dto.DestinationAllOnSearchDto;
import be.ecotravel.back.destination.dto.DestinationDetailsDto;
import be.ecotravel.back.destination.dto.SearchCriteria;
import be.ecotravel.back.entity.Destination;
import be.ecotravel.back.service.CloudinaryService;
import be.ecotravel.back.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/destination")
public class DestinationController {

    private final DestinationService destinationService;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public DestinationController(DestinationService destinationService, CloudinaryService cloudinaryService) {
        this.destinationService = destinationService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/popular-destination")
    public ResponseEntity<List<Destination>> destinations() {
        List<Destination> popularDestination = this.destinationService.getPopular();
        return new ResponseEntity<>(popularDestination, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DestinationAllOnSearchDto>> searchDestination(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) List<String> tags,
            @RequestParam(required = false) String type) {

        SearchCriteria searchCriteria = new SearchCriteria(query, tags, type);
        List<DestinationAllOnSearchDto> destinations = destinationService.searchDestinations(searchCriteria);
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationDetailsDto> getDestinationById(@PathVariable UUID id) {
        DestinationDetailsDto destination = destinationService.getDestinationDetails(id);
        if (destination == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> postDestination(@RequestBody DestinationCreationDto destinationDto) {
        UUID destinationId = destinationService.createDestination(destinationDto);
        return new ResponseEntity<>(destinationId, HttpStatus.CREATED);
    }
}
