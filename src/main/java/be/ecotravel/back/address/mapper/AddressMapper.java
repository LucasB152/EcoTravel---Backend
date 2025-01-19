package be.ecotravel.back.address.mapper;

import be.ecotravel.back.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(target = "id", ignore = true)
    Address toEntity(String country, String location, String street, String number, String zipcode, double longitude, double latitude);
}
