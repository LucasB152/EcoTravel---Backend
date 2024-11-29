package be.ecotravel.back.controller;

import be.ecotravel.back.request.dto.RequestCreationDto;
import be.ecotravel.back.service.RequestService;
import com.sun.net.httpserver.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/request")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<Void> postRequest(@RequestParam RequestCreationDto body) {
        requestService.createRequest(body);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
