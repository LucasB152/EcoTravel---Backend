package be.ecotravel.back.controller;

import be.ecotravel.back.entity.Itinerary;
import be.ecotravel.back.itinerary.dto.ItineraryCreationDto;
import be.ecotravel.back.itinerary.dto.ItineraryResponseDto;
import be.ecotravel.back.service.GoogleMapService;
import be.ecotravel.back.service.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
    public ResponseEntity<ItineraryResponseDto> getItinerary(@PathVariable UUID id) {
        return ResponseEntity.ok(itineraryService.getItinerary(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ItineraryResponseDto>> getUserItineraries(@PathVariable UUID userId) {
        return ResponseEntity.ok(itineraryService.getItineraryFromUser(userId));
    }

    @PostMapping()
    public ResponseEntity<Map<String, String>> createItinerary(@RequestBody ItineraryCreationDto dto) {
        itineraryService.createItinerary(dto);
        return ResponseEntity.ok(Map.of("Message", "L'itinéraire a bien été créé"));
    }

    @PutMapping()
    public ResponseEntity<ItineraryResponseDto> updateItinerary(@RequestBody Itinerary itinerary) {
        return null; //TODO
    }

    @DeleteMapping()
    public ResponseEntity<UUID> deleteItinerary(@RequestParam UUID id) {
        return null; //TODO
    }

}
