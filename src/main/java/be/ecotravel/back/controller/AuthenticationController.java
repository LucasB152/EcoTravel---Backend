package be.ecotravel.back.controller;

import be.ecotravel.back.exception.UserNotVerifiedException;
import be.ecotravel.back.service.EmailService;
import be.ecotravel.back.service.TokenService;
import be.ecotravel.back.security.LoginResponse;
import be.ecotravel.back.service.AuthenticationService;
import be.ecotravel.back.service.UserService;
import be.ecotravel.back.user.dto.UserLoginDto;
import be.ecotravel.back.user.dto.UserCreationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {
    private final TokenService tokenService;

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final EmailService emailService;

    public AuthenticationController(
            TokenService tokenService,
            AuthenticationService authenticationService,
            UserService userService,
            EmailService emailService
    ) {
        this.tokenService = tokenService;
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.emailService = emailService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserCreationDto userDto) {
        String hashedPassword = authenticationService.encodePassword(userDto.password());
        UUID userId = userService.createUser(userDto, hashedPassword);

        String token = tokenService.generateToken(userId);
        emailService.sendConfirmationEmail(userDto.email(), token);

        return ResponseEntity.status(200).body(Map.of("Message", "Email envoyé à " + userDto.email()));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody UserLoginDto userDto) {
        UUID userId = userService.getUserId(userDto.email());

        if (!userService.isUserVerified(userId)) {
            throw new UserNotVerifiedException("User is not verified");
        }

        authenticationService.authenticate(userDto);

        String token = tokenService.generateToken(userId);
        LoginResponse loginResponse = new LoginResponse(token, tokenService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/verify-email/{token}")
    public ResponseEntity<?> verifyEmail(@PathVariable String token){
        String userIdString = tokenService.extractUsername(token);
        userService.activateUser(UUID.fromString(userIdString));

        return ResponseEntity.ok(Map.of("Message", "Email verified"));
    }
}