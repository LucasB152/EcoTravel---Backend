package be.ecotravel.review.mapper;

import be.ecotravel.entity.Destination;
import be.ecotravel.entity.Review;
import be.ecotravel.entity.User;
import be.ecotravel.review.dto.ReviewCreationDto;
import be.ecotravel.review.dto.ReviewResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "edited", ignore = true)
    Review toEntity(ReviewCreationDto reviewDto, User user, Destination destination);

    @Mapping(target = "dateString", source = "review.createdAt", dateFormat = "dd-MM-yyyy HH:mm:ss")
    ReviewResponseDto toReviewResponseDto(Review review, String username);

}
