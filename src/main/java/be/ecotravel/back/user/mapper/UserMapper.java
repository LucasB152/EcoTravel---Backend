package be.ecotravel.back.user.mapper;

import be.ecotravel.back.entity.User;
import be.ecotravel.back.entity.UserRole;
import be.ecotravel.back.user.dto.UserCreationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "profilePicturePath", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "password", source = "hashedPassword")
    @Mapping(target = "activated", constant = "false")
    User toEntity(UserCreationDto userDto, String hashedPassword, UserRole userRole);

}
