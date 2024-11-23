package be.ecotravel.controller;

import be.ecotravel.dto.DestinationDto;
import be.ecotravel.review.dto.ReviewCreationDto;
import be.ecotravel.entity.Destination;
import be.ecotravel.service.DestinationService;
import be.ecotravel.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    private final DestinationService destinationService;

    private final ReviewService reviewService;

    @Autowired
    public DestinationController(DestinationService destinationService, ReviewService reviewService) {
        this.destinationService = destinationService;
        this.reviewService = reviewService;
    }

    @GetMapping("/popular-destination")
    public Destination[] destinations() {
        return destinationService.getPopular();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationDto> getDestinationById(@PathVariable UUID id) {
        DestinationDto destination = destinationService.getDestinationById(id);
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

    @PostMapping("/post-report")
    public void postReport() {
        //TODO
    }
}
