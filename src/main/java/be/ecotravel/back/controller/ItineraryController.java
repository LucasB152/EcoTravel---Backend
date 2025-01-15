package be.ecotravel.back.controller;

import be.ecotravel.back.itinerary.dto.ItineraryCreationDto;
import be.ecotravel.back.itinerary.dto.ItineraryResponseDto;
import be.ecotravel.back.service.GoogleMapService;
import be.ecotravel.back.service.ItineraryService;
import be.ecotravel.back.service.StepService;
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
    private final StepService stepService;
    private final GoogleMapService googleMapService;

    @Autowired
    public ItineraryController(ItineraryService itineraryService,
                               GoogleMapService googleMapService,
                               StepService stepService) {
        this.itineraryService = itineraryService;
        this.googleMapService = googleMapService;
        this.stepService = stepService;
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

    @PutMapping("/{itineraryId}/name")
    public ResponseEntity<Map<String, Object>> updateItineraryName(@PathVariable UUID itineraryId, @RequestBody String newTitle){
        itineraryService.updateItineraryName(itineraryId, newTitle);
        ItineraryResponseDto updatedItinerary = itineraryService.getItinerary(itineraryId);
        return ResponseEntity.ok(Map.of("Message", "Le nom de l'itinéraire a été modifié", "Itinerary", updatedItinerary));
    }

    @PutMapping("{itineraryId}/step/up")
    public ResponseEntity<Map<String, Object>> updateStepOrderUp(@PathVariable UUID itineraryId, @RequestBody String stepId){
        stepService.putStepUp(UUID.fromString(stepId), itineraryId);
        ItineraryResponseDto updatedItinerary = itineraryService.getItinerary(itineraryId);
        return ResponseEntity.ok(Map.of("Message", "Le nom de l'itinéraire a été modifié", "Itinerary", updatedItinerary));
    }

    @PutMapping("{itineraryId}/step/down")
    public ResponseEntity<Map<String, Object>> updateStepOrderDown(@PathVariable UUID itineraryId, @RequestBody String stepId){
        stepService.putStepDown(UUID.fromString(stepId), itineraryId);
        ItineraryResponseDto updatedItinerary = itineraryService.getItinerary(itineraryId);
        return ResponseEntity.ok(Map.of("Message", "Le nom de l'itinéraire a été modifié", "Itinerary", updatedItinerary));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteItinerary(@PathVariable UUID id) {
        itineraryService.deleteItinerary(id);
        return ResponseEntity.ok(Map.of("Message", "L'itinéraire a bien été supprimé"));
    }

    @DeleteMapping("{itineraryId}/step/{stepId}")
    public ResponseEntity<Map<String, Object>> deleteStepFromItinerary(@PathVariable UUID itineraryId, @PathVariable UUID stepId) {
        stepService.deleteStepFromItinerary(stepId, itineraryId);
        ItineraryResponseDto updatedItinerary = itineraryService.getItinerary(itineraryId);
        return ResponseEntity.ok(Map.of("Message", "L'étape à été supprimée de l'itinéraire", "Itinerary", updatedItinerary));
    }
}
