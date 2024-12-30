package be.ecotravel.back.controller;

import be.ecotravel.back.review.dto.ReviewCreationDto;
import be.ecotravel.back.review.dto.ReviewDeleteDto;
import be.ecotravel.back.review.dto.ReviewEditDto;
import be.ecotravel.back.review.dto.ReviewResponseDto;
import be.ecotravel.back.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createReview(@RequestBody ReviewCreationDto review) {
        reviewService.createReview(review);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public void updateReview(@RequestBody ReviewEditDto review) {
        reviewService.editReview(review);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void deleteReview(@RequestBody ReviewDeleteDto review) {
        reviewService.deleteReview(review);
    }

    @GetMapping("/{destinationId}")
    public List<ReviewResponseDto> getDestinationReviews(@PathVariable UUID destinationId) {
        return reviewService.getDestinationReviews(destinationId);
    }

}
