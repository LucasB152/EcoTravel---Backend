package be.ecotravel.back.controller;

import be.ecotravel.back.destination.dto.DestinationCreationDto;
import be.ecotravel.back.entity.Destination;
import be.ecotravel.back.review.dto.ReviewCreationDto;
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

    @Autowired
    private DestinationService destinationService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/popular-destination")
    public ResponseEntity<List<Destination>> destinations() {
        List<Destination> popularDestination = this.destinationService.getPopular();
        return new ResponseEntity<>(popularDestination, HttpStatus.OK);
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

    @GetMapping("/reviews/{id}")
    public void getReviews() {
        //TODO
    }

    @PostMapping("/post-review")
    public void postReview(@RequestBody ReviewCreationDto reviewDto) {
        reviewService.createReview(reviewDto);
    }
}
