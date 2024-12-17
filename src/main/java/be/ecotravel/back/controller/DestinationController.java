package be.ecotravel.back.controller;

import be.ecotravel.back.destination.dto.DestinationOnMapDto;
import be.ecotravel.back.destination.dto.DestinationCreationDto;
import be.ecotravel.back.entity.Destination;
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

    @Autowired
    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping("/popular-destination")
    public ResponseEntity<List<Destination>> destinations() {
        List<Destination> popularDestination = this.destinationService.getPopular();
        return new ResponseEntity<>(popularDestination, HttpStatus.OK);
    }

    /**
     * Récupère les destinations pour la page d'accueil (pour la map)
     * @return liste de DestinationOnMapDto
     */
    @GetMapping("/destination-onmap")
    public ResponseEntity<List<DestinationOnMapDto>> destinationOnMap() {
        //todo remove this (debug)
        List<DestinationOnMapDto> destinationOnMapTEMP = List.of(
                new DestinationOnMapDto(UUID.randomUUID(), 50.7636, 5.5273, "maison en foret","tres tres jolie maison en foret avec les ours", "host", List.of("https://www.houseplans.net/uploads/plans/32005/elevations/88909-768.jpg", "https://casaeconstrucao.org/wp-content/uploads/2020/03/casas-baratas-tiny-house-no-jardim.jpg")),
                new DestinationOnMapDto(UUID.randomUUID(), 50.66036, 5.5993, "acrobranche","acrobranche AGILE dans les arbres", "activity", List.of("https://ecopark-adventures.com/wp-content/uploads/2019/07/HP-Main-picture-Tournai-e1580480117562.jpg")));

        return new ResponseEntity<>(destinationOnMapTEMP, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destination> getDestinationById(@PathVariable UUID id) {
        Destination destination = destinationService.getDestinationById(id);
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
