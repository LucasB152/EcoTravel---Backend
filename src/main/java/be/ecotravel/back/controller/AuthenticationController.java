package be.ecotravel.back.controller;

import be.ecotravel.back.entity.User;
import be.ecotravel.back.exception.AuthenticationException;
import be.ecotravel.back.service.JwtService;
import be.ecotravel.back.security.LoginResponse;
import be.ecotravel.back.service.AuthenticationService;
import be.ecotravel.back.service.UserService;
import be.ecotravel.back.user.dto.LoginUserDto;
import be.ecotravel.back.user.dto.UserCreationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;
    private final UserService userService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, UserService userService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserCreationDto registerUserCreationDto) {
        String message;

        try {
            message = userService.createUser(registerUserCreationDto);
        } catch (AuthenticationException exception) {
            return ResponseEntity.status(400).body(exception.getMessage());
        }

        return ResponseEntity.status(200).body(Map.of("Message", message));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = null;
        try {
            authenticatedUser = authenticationService.authenticate(loginUserDto);
        } catch (AuthenticationException exception) {
            return ResponseEntity.status(400).body(exception.getMessage());
        }

        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/verify-email/{token}")
    public ResponseEntity<?> verifyEmail(@PathVariable String token){
        try {
            authenticationService.verifyEmail(token);
        } catch (AuthenticationException exception) {
            return ResponseEntity.status(400).body(exception.getMessage());
        }
        return ResponseEntity.ok("Email verification successful");
    }
}