package be.ecotravel.back.controller;

import be.ecotravel.back.entity.Itinerary;
import be.ecotravel.back.itinerary.dto.ItineraryCreationDto;
import be.ecotravel.back.itinerary.dto.ItineraryDetailsResponseDto;
import be.ecotravel.back.itinerary.dto.ItineraryListResponseDto;
import be.ecotravel.back.service.GoogleMapService;
import be.ecotravel.back.service.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/itinerary")
public class ItineraryController {

    private final ItineraryService itineraryService;
    private final GoogleMapService googleMapService;

    @Autowired
    public ItineraryController(ItineraryService itineraryService, GoogleMapService googleMapService) {
        this.itineraryService = itineraryService;
        this.googleMapService = googleMapService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItineraryDetailsResponseDto> getItinerary(@PathVariable UUID id) {
        return null; //TODO
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ItineraryListResponseDto>> getUserItineraries(@PathVariable UUID userId) {
        return ResponseEntity.ok(itineraryService.getItineraryFromUser(userId));
    }

    @PostMapping()
    public ResponseEntity<UUID> createItinerary(@RequestBody ItineraryCreationDto dto) {
        UUID itineraryId = itineraryService.createItinerary(dto);
        return null; //TODO
    }

    @PutMapping()
    public ResponseEntity<ItineraryDetailsResponseDto> updateItinerary(@RequestBody Itinerary itinerary) {
        return null; //TODO
    }

    @DeleteMapping()
    public ResponseEntity<UUID> deleteItinerary(@RequestParam UUID id) {
        return null; //TODO
    }

}
