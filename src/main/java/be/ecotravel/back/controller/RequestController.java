package be.ecotravel.back.controller;

import be.ecotravel.back.entity.RequestStatusEnum;
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
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/status")
    public ResponseEntity<RequestStatusEnum> getStatusFromRequest(@PathVariable UUID userId){
        RequestStatusEnum status = requestService.getRequestStatusFromUser(userId);
        return ResponseEntity.ok(status);
    }

    @PutMapping("/{requestId}")
    public ResponseEntity<Map<String, Object>> putRequest(@PathVariable String requestId, @RequestBody RequestPutDto dto) {
        List<RequestResponseDto> updatedRequestList = requestService.updateRequest(UUID.fromString(requestId), dto);
        return ResponseEntity.ok(Map.of("Message", "Le status de la requête a été mis à jour", "Requests", updatedRequestList));
    }

}
