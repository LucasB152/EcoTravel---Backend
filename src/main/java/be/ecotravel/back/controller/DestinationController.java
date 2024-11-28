package be.ecotravel.back.controller;

import be.ecotravel.back.destination.dto.DestinationResponseDto;
import be.ecotravel.back.review.dto.ReviewCreationDto;
import be.ecotravel.back.service.DestinationService;
import be.ecotravel.back.service.ReviewService;
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
    public HttpStatus destinations() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationResponseDto> getDestinationById(@PathVariable UUID id) {
        DestinationResponseDto destination = destinationService.getDestinationById(id);
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
