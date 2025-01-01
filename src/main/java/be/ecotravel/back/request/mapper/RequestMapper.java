package be.ecotravel.back.request.mapper;

import be.ecotravel.back.entity.Request;
import be.ecotravel.back.entity.User;
import be.ecotravel.back.request.dto.RequestCreationDto;
import be.ecotravel.back.request.dto.RequestResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "websiteUrl", source = "requestDto.website")
    @Mapping(target = "services", source = "servicesValue")
    Request toEntity(RequestCreationDto requestDto, int servicesValue, User user);

    @Mapping(target = "status", source = "status")
    @Mapping(target = "userFullName", source = "userFullName")
    RequestResponseDto toResponseDto(Request request, String status, String userFullName);

}
