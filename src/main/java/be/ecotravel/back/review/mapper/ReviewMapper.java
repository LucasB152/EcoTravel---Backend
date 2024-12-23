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
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "edited", ignore = true)
    Review toEntity(ReviewCreationDto reviewDto, User user, Destination destination);

    @Mapping(target = "dateString", source = "review.createdAt", dateFormat = "dd-MM-yyyy HH:mm:ss")
    ReviewResponseDto toReviewResponseDto(Review review, String username);

}
