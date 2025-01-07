package be.ecotravel.back.request.mapper;

import be.ecotravel.back.entity.HostStatusEnum;
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
    @Mapping(target = "contactPhone", source = "requestDto.phone")
    @Mapping(target = "hostStatus", source = "status")
    Request toEntity(RequestCreationDto requestDto, int servicesValue, User user, HostStatusEnum status);

    @Mapping(target = "userFullName", source = "userFullName")
    @Mapping(target = "services", source = "services")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "hostStatus", source = "hostStatus")
    RequestResponseDto toResponseDto(Request request, String hostStatus, String userFullName, String email, String[] services);

}
