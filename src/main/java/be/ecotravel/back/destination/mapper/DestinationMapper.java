package be.ecotravel.back.destination.mapper;

import be.ecotravel.back.destination.dto.DestinationResponseDto;
import be.ecotravel.back.entity.Destination;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DestinationMapper {

    Destination toEntity(DestinationResponseDto dto);

    DestinationResponseDto toResponseDto(Destination entity);

}
