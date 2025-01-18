package be.ecotravel.back.controller;

import be.ecotravel.back.destination.dto.DestinationCreationDto;
import be.ecotravel.back.destination.dto.DestinationResponseDto;
import be.ecotravel.back.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/api/host")
@RestController
public class HostController {
    private final DestinationService destinationService;

    @Autowired
    public HostController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @PostMapping("/{hostId}/destination")
    public ResponseEntity<Map<String, UUID>> postDestination(@PathVariable UUID hostId, @RequestBody DestinationCreationDto destinationDto) {
        UUID destinationId = destinationService.createDestination(destinationDto, hostId);
        return ResponseEntity.ok(Map.of("destinationId", destinationId));
    }

    @PostMapping("/destinations/pictures/{destinationId}")
    public ResponseEntity<?> postDestinationPicture(@PathVariable UUID destinationId, @RequestParam("file") MultipartFile file){
        return ResponseEntity.ok(destinationService.addDestinationPicture(destinationId, file));
    }

    @GetMapping("/{hostId}/destination")
    public ResponseEntity<List<DestinationResponseDto>> getDestinationsFromHost(@PathVariable UUID hostId){
        List<DestinationResponseDto> response = destinationService.getDestinationFromHost(hostId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{hostId}/destination/{destinationId}")
    public ResponseEntity<List<DestinationResponseDto>> deleteDestination(@PathVariable UUID hostId, @PathVariable UUID destinationId){
        destinationService.deleteDestination(destinationId);
        return ResponseEntity.ok(destinationService.getDestinationFromHost(hostId));
    }
}
