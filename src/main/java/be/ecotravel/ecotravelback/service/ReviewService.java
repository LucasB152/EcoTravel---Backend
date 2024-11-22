package be.ecotravel.ecotravelback.service;

import be.ecotravel.ecotravelback.dto.ReviewDto;
import be.ecotravel.ecotravelback.entity.Destination;
import be.ecotravel.ecotravelback.entity.Review;
import be.ecotravel.ecotravelback.entity.User;
import be.ecotravel.ecotravelback.repository.DestinationRepository;
import be.ecotravel.ecotravelback.repository.ReviewRepository;
import be.ecotravel.ecotravelback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DestinationRepository destinationRepository;

    public void createReview(ReviewDto reviewDto) {
        User user = userRepository.getReferenceById(reviewDto.userId());
        Destination destination = destinationRepository.getReferenceById(reviewDto.destinationId());

        Review review = Mapper.reviewDtoToReview(reviewDto, user, destination);

        reviewRepository.save(review);
    }

    public void editReview(ReviewDto reviewDto) {
        //TODO
    }

    public void getDestinationReviews() {
        //TODO
    }

}
