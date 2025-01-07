package be.ecotravel.back.controller;

import be.ecotravel.back.request.dto.RequestCreationDto;
import be.ecotravel.back.request.dto.RequestPutDto;
import be.ecotravel.back.request.dto.RequestResponseDto;
import be.ecotravel.back.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/request")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> postRequest(@RequestBody RequestCreationDto dto) {
        UUID requestId = requestService.createRequest(dto);
        return ResponseEntity.ok(Map.of("requestId", requestId.toString()));
    }

    @PostMapping("/files/{requestId}")
    public ResponseEntity<List<String>> postCertificationFiles(@PathVariable String requestId, @RequestParam("file") MultipartFile file){
        return ResponseEntity.ok(requestService.addCertificationFile(requestId, file));
    }

    @GetMapping()
    public ResponseEntity<List<RequestResponseDto>> getAllRequests() {
        List<RequestResponseDto> requests = requestService.getAllRequests();
        return new ResponseEntity<>(requests, HttpStatus.FOUND);
    }

    @PutMapping()
    public ResponseEntity<Void> putRequest(@RequestBody RequestPutDto dto) {
        requestService.updateRequest(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
