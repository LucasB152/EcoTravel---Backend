package be.ecotravel.back.controller;

import be.ecotravel.back.entity.Tag;
import be.ecotravel.back.service.TagService;
import be.ecotravel.back.tag.dto.TagCreationDto;
import be.ecotravel.back.tag.dto.TagResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/api/tag")
@RestController
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping()
    public ResponseEntity<List<TagResponseDto>> tags() {
        List<TagResponseDto> tags = this.tagService.getAll();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponseDto> getTagById(@PathVariable UUID id) {
        TagResponseDto tags = this.tagService.getTagDetails(id);
        if (tags == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> postTag(@RequestBody TagCreationDto tag){
        List<TagResponseDto> updatedTagList = this.tagService.postTag(tag);
        return ResponseEntity.ok(Map.of("Message", "Le tag a bien été ajouté", "Tags", updatedTagList));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable String id){
        try {
            List<TagResponseDto> updatedTagList = this.tagService.deleteTag(UUID.fromString(id));
            return ResponseEntity.ok(Map.of("Message", "Le tag a bien été supprimé", "Tags", updatedTagList));
        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
