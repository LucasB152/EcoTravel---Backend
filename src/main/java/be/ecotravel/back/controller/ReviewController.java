package be.ecotravel.back.controller;

import be.ecotravel.back.review.dto.ReviewCreationDto;
import be.ecotravel.back.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/create-review")
    @ResponseStatus(HttpStatus.CREATED)
    public void createReview(@RequestBody ReviewCreationDto reviewDto) {
        reviewService.createReview(reviewDto);
    }

}
