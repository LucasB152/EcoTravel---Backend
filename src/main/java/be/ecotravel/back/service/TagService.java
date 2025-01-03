package be.ecotravel.back.service;


import be.ecotravel.back.entity.Tag;
import be.ecotravel.back.repository.TagRepository;
import be.ecotravel.back.tag.dto.TagCreationDto;
import be.ecotravel.back.tag.dto.TagResponseDto;
import be.ecotravel.back.tag.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TagService {

    private final TagMapper tagMapper;
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagMapper tagMapper, TagRepository tagRepository) {
        this.tagMapper = tagMapper;
        this.tagRepository = tagRepository;
    }

    public List<TagResponseDto> getAll() {
        return this.tagRepository.findAll().stream()
                .map(this.tagMapper::toTagResponseDto)
                .collect(Collectors.toList());
    }

    public TagResponseDto getTagDetails(UUID id) {
        return this.tagRepository.findById(id)
                .map(this.tagMapper::toTagResponseDto)
                .orElse(null);
    }

    public List<TagResponseDto> deleteTag(UUID id){
        this.tagRepository.deleteById(id);
        return getAll();
    }

    public List<TagResponseDto> postTag(TagCreationDto tagCreationDto){
        Tag tag = tagMapper.toEntity(tagCreationDto);
        this.tagRepository.save(tag);
        return getAll();
    }
}
