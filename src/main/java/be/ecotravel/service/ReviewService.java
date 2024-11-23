package be.ecotravel.service;

import be.ecotravel.review.dto.ReviewCreationDto;
import be.ecotravel.entity.Destination;
import be.ecotravel.entity.Review;
import be.ecotravel.entity.User;
import be.ecotravel.repository.DestinationRepository;
import be.ecotravel.repository.ReviewRepository;
import be.ecotravel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private final DestinationRepository destinationRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, DestinationRepository destinationRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.destinationRepository = destinationRepository;
    }

    public void createReview(ReviewCreationDto reviewDto) {
        User user = userRepository.getReferenceById(reviewDto.userId());
        Destination destination = destinationRepository.getReferenceById(reviewDto.destinationId());

        Review review = Mapper.reviewDtoToReview(reviewDto, user, destination);

        reviewRepository.save(review);
    }

    public void editReview(ReviewCreationDto reviewDto) {
        //TODO
    }

    public void getDestinationReviews() {
        //TODO
    }

}
