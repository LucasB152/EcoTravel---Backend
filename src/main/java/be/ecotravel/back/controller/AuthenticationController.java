package be.ecotravel.back.controller;

import be.ecotravel.back.security.LoginResponse;
import be.ecotravel.back.service.AuthenticationService;
import be.ecotravel.back.service.UserService;
import be.ecotravel.back.user.dto.UserLoginDto;
import be.ecotravel.back.user.dto.UserCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserCreationDto userDto) {
        authenticationService.signup(userDto);
        return ResponseEntity.status(201).body(Map.of("Message", "Confirmation email sent to " + userDto.email()));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody UserLoginDto userDto) {
        LoginResponse loginResponse = authenticationService.login(userDto);
        return ResponseEntity.status(200).body(loginResponse);
    }

    @GetMapping("/verify-email/{token}")
    public ResponseEntity<?> verifyEmail(@PathVariable String token){
        userService.activateUser(token);
        return ResponseEntity.ok(Map.of("Message", "Email verified"));
    }
}