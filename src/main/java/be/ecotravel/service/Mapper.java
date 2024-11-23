package be.ecotravel.service;

import be.ecotravel.dto.DestinationDto;
import be.ecotravel.review.dto.ReviewCreationDto;
import be.ecotravel.dto.UserDto;
import be.ecotravel.entity.Destination;
import be.ecotravel.entity.Review;
import be.ecotravel.entity.User;

import java.util.UUID;

public class Mapper {

    //region Users

    public static User userDtoToUser(UserDto userDto) {
        return null; //TODO
    }

    public static UserDto userToUserDto(User user) {
        return null; //TODO
    }

    //endregion

    //region Destinations
    public static Destination destinationDtoToDestination(DestinationDto destinationDto) {
        return null;
    }

    public static DestinationDto destinationToDestinationDto(Destination destination) {
        return null;
    }
    //endregion

    //region Reviews
    public static ReviewCreationDto reviewToReviewDto(Review review) {
        return null; //TODO
    }

    public static Review reviewDtoToReview(ReviewCreationDto reviewDto, User user, Destination destination) {
        UUID reviewId = reviewDto.id() == null ? UUID.randomUUID() : reviewDto.id();

        Review review = new Review(
                reviewDto.score(),
                reviewDto.comment(),
                reviewDto.createdAt(),
                reviewDto.isEdited(),
                user,
                destination
        );

        review.setId(reviewId);

        return review;
    }

    //endregion
}
