package be.ecotravel.ecotravelback.service;

import be.ecotravel.ecotravelback.dto.DestinationDto;
import be.ecotravel.ecotravelback.dto.ReportDto;
import be.ecotravel.ecotravelback.dto.ReviewDto;
import be.ecotravel.ecotravelback.dto.UserDto;
import be.ecotravel.ecotravelback.entity.Destination;
import be.ecotravel.ecotravelback.entity.Report;
import be.ecotravel.ecotravelback.entity.Review;
import be.ecotravel.ecotravelback.entity.User;

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

    //region Reports
    public static Report reportDtoToReport(ReportDto reportDto, User user, Destination destination) {
        return new Report(
                reportDto.id(),
                reportDto.text(),
                reportDto.isEdited(),
                reportDto.date(),
                user,
                destination
        );
    }
    //endregion

    //region Reviews
    public static ReviewDto reviewToReviewDto(Review review) {
        return null; //TODO
    }

    public static Review reviewDtoToReview(ReviewDto reviewDto, User user, Destination destination) {
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
