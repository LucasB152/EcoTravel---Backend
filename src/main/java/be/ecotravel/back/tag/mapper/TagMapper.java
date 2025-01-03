package be.ecotravel.back.tag.mapper;


import be.ecotravel.back.entity.Tag;
import be.ecotravel.back.tag.dto.TagCreationDto;
import be.ecotravel.back.tag.dto.TagResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagMapper {

    @Mapping(target = "id", ignore = true)
    Tag toEntity(String name);

    TagResponseDto toTagResponseDto(Tag tag);

    Tag toEntity(TagCreationDto tagCreationDto);
}
