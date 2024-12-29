package be.ecotravel.back.service;

import be.ecotravel.back.exception.AuthenticationException;
import be.ecotravel.back.review.dto.ReviewCreationDto;
import be.ecotravel.back.entity.Destination;
import be.ecotravel.back.entity.Review;
import be.ecotravel.back.entity.User;
import be.ecotravel.back.repository.DestinationRepository;
import be.ecotravel.back.repository.ReviewRepository;
import be.ecotravel.back.repository.UserRepository;
import be.ecotravel.back.review.dto.ReviewEditDto;
import be.ecotravel.back.review.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final DestinationRepository destinationRepository;

    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, DestinationRepository destinationRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.destinationRepository = destinationRepository;
        this.reviewMapper = reviewMapper;
    }

    public void createReview(ReviewCreationDto reviewDto) {
        User user = userRepository.getReferenceById(reviewDto.userId());
        Destination destination = destinationRepository.getReferenceById(reviewDto.destinationId());

        Review review = reviewMapper.toEntity(reviewDto, user, destination);
        reviewRepository.save(review);
    }

    public void editReview(ReviewEditDto reviewDto) {
        Review review = reviewRepository.getReferenceById(reviewDto.id());
        User reviewUser = review.getUser();

        if (!reviewUser.getId().equals(reviewDto.userId())) {
            throw new AuthenticationException("You are not allowed to edit this review.");
        }

        review.setTitle(reviewDto.title());
        review.setComment(reviewDto.comment());
        review.setScore(reviewDto.score());
        review.setEdited(true);
        review.setEditedAt(LocalDateTime.now());

        reviewRepository.save(review);
    }

    public void getDestinationReviews() {
        //TODO
    }

}
