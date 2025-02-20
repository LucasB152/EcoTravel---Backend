package be.ecotravel.back.destination.mapper;

import be.ecotravel.back.destination.dto.DestinationCreationDto;
import be.ecotravel.back.destination.dto.DestinationDetailsDto;
import be.ecotravel.back.destination.dto.DestinationResponseDto;
import be.ecotravel.back.destination.dto.DestinationSearchDto;
import be.ecotravel.back.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface DestinationMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "destinationType", source = "type")
    @Mapping(target = "address", source = "address")
    Destination toEntity(DestinationCreationDto dto, DestinationType type, User user, Set<Tag> tag, Address address);

    @Mapping(target = "destinationID", source = "id")
    @Mapping(target = "latitude", source = "address.latitude")
    @Mapping(target = "longitude", source = "address.longitude")
    @Mapping(target = "images", ignore = true)
    DestinationSearchDto toSearchDto(Destination entity);

    @Mapping(target = "destinationID", source = "id")
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "destinationType", source = "destinationType.type")
    @Mapping(target = "address", source = "address.street")
    @Mapping(target = "latitude", source = "address.latitude")
    @Mapping(target = "longitude", source = "address.longitude")
    @Mapping(target = "tags", ignore = true)
    DestinationDetailsDto toDetailsDto(Destination entity);

    @Mapping(target = "destinationID", source = "id")
    DestinationResponseDto toDestinationResponseDto(Destination destination);
}
