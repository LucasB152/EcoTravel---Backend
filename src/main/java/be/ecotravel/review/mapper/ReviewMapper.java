package be.ecotravel.review.mapper;

import be.ecotravel.entity.Review;
import org.mapstruct.Mapper;

@Mapper
public interface ReviewMapper {

    Review toEntity();

}
