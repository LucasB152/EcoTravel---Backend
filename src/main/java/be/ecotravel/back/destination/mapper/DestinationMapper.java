package be.ecotravel.back.destination.mapper;

import be.ecotravel.back.address.mapper.AddressMapper;
import be.ecotravel.back.destination.dto.DestinationCreationDto;
import be.ecotravel.back.destination.dto.DestinationDetailsDto;
import be.ecotravel.back.destination.dto.DestinationResponseDto;
import be.ecotravel.back.destination.dto.DestinationSearchDto;
import be.ecotravel.back.entity.Destination;
import be.ecotravel.back.entity.DestinationType;
import be.ecotravel.back.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface DestinationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "imageFolderPath", ignore = true)
    @Mapping(target = "address", source = "dto.addressCreationDto")
    @Mapping(target = "destinationType", source = "type")
    Destination toEntity(DestinationCreationDto dto, DestinationType type, User user);

    DestinationResponseDto toResponseDto(Destination entity);

    @Mapping(target = "destinationID", source = "id")
    @Mapping(target = "images", expression = "java(new String[]{entity.getImageFolderPath()})")
    @Mapping(target = "latitude", source = "address.latitude")
    @Mapping(target = "longitude", source = "address.longitude")
    DestinationSearchDto toSearchDto(Destination entity);

    @Mapping(target = "destinationID", source = "id")
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "destinationType", source = "destinationType.type")
    @Mapping(target = "address", source = "address.street")
    DestinationDetailsDto toDetailsDto(Destination entity);

}
