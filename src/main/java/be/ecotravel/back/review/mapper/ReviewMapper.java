package be.ecotravel.back.review.mapper;

import be.ecotravel.back.entity.Destination;
import be.ecotravel.back.entity.Review;
import be.ecotravel.back.entity.User;
import be.ecotravel.back.review.dto.ReviewCreationDto;
import be.ecotravel.back.review.dto.ReviewResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "edited", ignore = true)
    @Mapping(target = "editedAt", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "destination", source = "destination")
    Review toEntity(ReviewCreationDto reviewDto, User user, Destination destination);

    @Mapping(target = "dateStringCreation", source = "review.createdAt", dateFormat = "dd-MM-yyyy HH:mm:ss")
    @Mapping(target = "dateStringModification", source = "review.editedAt", dateFormat = "dd-MM-yyyy HH:mm:ss")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    ReviewResponseDto toReviewResponseDto(Review review);

}
