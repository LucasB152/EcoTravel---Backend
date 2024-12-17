package be.ecotravel.back.service;

import be.ecotravel.back.exception.UserNotVerifiedException;
import be.ecotravel.back.security.LoginResponse;
import be.ecotravel.back.user.dto.UserCreationDto;
import be.ecotravel.back.user.dto.UserLoginDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final UserService userService;
    private final EmailService emailService;

    public AuthenticationService(AuthenticationManager authenticationManager,
                                 PasswordEncoder passwordEncoder,
                                 TokenService tokenService,
                                 UserService userService,
                                 EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.userService = userService;
        this.emailService = emailService;
    }

    public void signup(UserCreationDto userDto){
        String hashedPassword = encodePassword(userDto.password());
        UUID userId = userService.createUser(userDto, hashedPassword);

        String token = tokenService.generateToken(userId);
        emailService.sendConfirmationEmail(userDto.email(), token);
    }

    public LoginResponse login(UserLoginDto userDto){
        UUID userId = userService.getUserId(userDto.email());

        if (!userService.isUserVerified(userId)) {
            throw new UserNotVerifiedException("User is not verified");
        }

        authenticate(userDto);

        String token = tokenService.generateToken(userId);
        return new LoginResponse(token, tokenService.getExpirationTime());
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private void authenticate(UserLoginDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.email(), input.password())
        );
    }
}
