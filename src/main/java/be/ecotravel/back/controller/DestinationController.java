package be.ecotravel.back.controller;

import be.ecotravel.back.destination.dto.DestinationCreationDto;
import be.ecotravel.back.destination.dto.DestinationSearchDto;
import be.ecotravel.back.destination.dto.DestinationDetailsDto;
import be.ecotravel.back.service.CloudinaryService;
import be.ecotravel.back.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping
    public ResponseEntity<Page<DestinationSearchDto>> searchDestinations(
            @RequestParam(value = "q", required = false) String query,
            @RequestParam(value = "tags", required = false) List<String> tags,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(destinationService.searchDestinations(query, tags, type, page, size));
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

    @GetMapping("/types")
    public ResponseEntity<List<String>> getDestinationTypes() {
        List<String> types = destinationService.getDestinationTypes();
        return new ResponseEntity<>(types, HttpStatus.OK);
    }
}
