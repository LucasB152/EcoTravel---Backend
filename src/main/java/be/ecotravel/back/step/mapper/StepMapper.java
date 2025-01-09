package be.ecotravel.back.step.mapper;

import be.ecotravel.back.entity.Destination;
import be.ecotravel.back.entity.Itinerary;
import be.ecotravel.back.entity.Step;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StepMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "destination", source = "destination")
    @Mapping(target = "itinerary", source = "itinerary")
    Step toEntity(int orderSequence, Destination destination, Itinerary itinerary);
}
