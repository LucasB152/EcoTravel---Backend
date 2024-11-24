package be.ecotravel.destination.mapper;

import be.ecotravel.destination.dto.DestinationResponseDto;
import be.ecotravel.entity.Destination;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DestinationMapper {

    Destination toEntity(DestinationResponseDto dto);

    DestinationResponseDto toResponseDto(Destination entity);

}
