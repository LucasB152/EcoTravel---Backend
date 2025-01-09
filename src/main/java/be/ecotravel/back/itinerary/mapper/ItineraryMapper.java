package be.ecotravel.back.itinerary.mapper;

import be.ecotravel.back.entity.Destination;
import be.ecotravel.back.entity.Itinerary;
import be.ecotravel.back.entity.User;
import be.ecotravel.back.itinerary.dto.ItineraryCreationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItineraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sharedUsers", ignore = true)
    @Mapping(target = "distance", ignore = true)
    Itinerary toEntity(ItineraryCreationDto itineraryCreationDto, User ownerUser, Destination startDestination, Destination endDestination);

}
