package be.ecotravel.back.itinerary.mapper;

import be.ecotravel.back.entity.Itinerary;
import be.ecotravel.back.entity.User;
import be.ecotravel.back.itinerary.dto.ItineraryCreationDto;
import be.ecotravel.back.itinerary.dto.ItineraryResponseDto;
import be.ecotravel.back.step.dto.StepResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItineraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "distance", source="distance")
    Itinerary toEntity(ItineraryCreationDto itineraryCreationDto, User ownerUser, int distance);

    @Mapping(target = "steps", source = "steps")
    ItineraryResponseDto toItineraryResponse(Itinerary itinerary, List<StepResponse> steps);

}
