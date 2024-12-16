package be.ecotravel.back.address.mapper;

import be.ecotravel.back.address.dto.AddressCreationDto;
import be.ecotravel.back.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toEntity(AddressCreationDto addressDto);

}
