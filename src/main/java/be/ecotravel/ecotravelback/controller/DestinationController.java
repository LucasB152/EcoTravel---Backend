package be.ecotravel.ecotravelback.controller;

import be.ecotravel.ecotravelback.dto.DestinationDto;
import be.ecotravel.ecotravelback.dto.ReviewDto;
import be.ecotravel.ecotravelback.entity.Destination;
import be.ecotravel.ecotravelback.service.DestinationService;
import be.ecotravel.ecotravelback.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    @Autowired
    DestinationService destinationService;

    @Autowired
    ReviewService reviewService;

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
    public void postReview(@RequestBody ReviewDto reviewDto) {
        reviewService.createReview(reviewDto);
    }

    @PostMapping("/post-report")
    public void postReport() {
        //TODO
    }
}
