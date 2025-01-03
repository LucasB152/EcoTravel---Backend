package be.ecotravel.back.controller;

import be.ecotravel.back.request.dto.RequestCreationDto;
import be.ecotravel.back.request.dto.RequestPutDto;
import be.ecotravel.back.request.dto.RequestResponseDto;
import be.ecotravel.back.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<Void> postRequest(@RequestBody RequestCreationDto dto) {
        requestService.createRequest(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
