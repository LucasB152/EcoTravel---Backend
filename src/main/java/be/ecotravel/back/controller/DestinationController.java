package be.ecotravel.back.controller;

import be.ecotravel.back.destination.dto.DestinationResponseDto;
import be.ecotravel.back.entity.Destination;
import be.ecotravel.back.review.dto.ReviewCreationDto;
import be.ecotravel.back.service.DestinationService;
import be.ecotravel.back.service.ReviewService;
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

    private final ReviewService reviewService;

    @Autowired
    public DestinationController(DestinationService destinationService, ReviewService reviewService) {
        this.destinationService = destinationService;
        this.reviewService = reviewService;
    }

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

    @GetMapping("/reviews/{id}")
    public void getReviews() {
        //TODO
    }

    @PostMapping("/post-review")
    public void postReview(@RequestBody ReviewCreationDto reviewDto) {
        reviewService.createReview(reviewDto);
    }
}
