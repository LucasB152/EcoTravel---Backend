package be.ecotravel.back.service;

import be.ecotravel.back.entity.*;
import be.ecotravel.back.exception.AuthenticationException;
import be.ecotravel.back.review.dto.ReviewCreationDto;
import be.ecotravel.back.repository.DestinationRepository;
import be.ecotravel.back.repository.ReviewRepository;
import be.ecotravel.back.repository.UserRepository;
import be.ecotravel.back.review.dto.ReviewDeleteDto;
import be.ecotravel.back.review.dto.ReviewEditDto;
import be.ecotravel.back.review.dto.ReviewResponseDto;
import be.ecotravel.back.review.mapper.ReviewMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        User user = userRepository.findById(reviewDto.userId())
                .orElseThrow(EntityNotFoundException::new);

        Destination destination = destinationRepository.findById(reviewDto.destinationId())
                .orElseThrow(() -> new EntityNotFoundException("Destination not found"));

        Review review = reviewMapper.toEntity(reviewDto, user, destination);
        reviewRepository.save(review);
    }

    public void editReview(ReviewEditDto reviewDto) {
        Review review = reviewRepository.findById(reviewDto.id())
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));

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

    public void deleteReview(ReviewDeleteDto reviewDto) {
        Review review = reviewRepository.findById(reviewDto.reviewId())
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));

        User reviewUser = review.getUser();

        User requestUser = userRepository.findById(reviewDto.userId())
                .orElseThrow(EntityNotFoundException::new);

        UserRole requestUserRole = requestUser.getUserRole();

        if (!reviewUser.getId().equals(requestUser.getId())
        && requestUserRole.getName() != UserRoleEnum.ADMIN) {
            throw new AuthenticationException("You are not allowed to delete this review.");
        }

        reviewRepository.delete(review);
    }

    public List<ReviewResponseDto> getDestinationReviews(UUID destinationId) {
        Destination destination = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new EntityNotFoundException("Destination not found"));

        List<Review> reviews = reviewRepository.findByDestination(destination);

        return reviews.stream()
                .map(review -> {
                    User user = review.getUser();
                    String username = user.getFirstName() + " " + user.getLastName();

                    return reviewMapper.toReviewResponseDto(review, username);
                })
                .toList();
    }

}
