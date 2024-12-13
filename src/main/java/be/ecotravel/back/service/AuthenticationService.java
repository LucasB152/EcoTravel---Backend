package be.ecotravel.back.service;

import be.ecotravel.back.entity.User;
import be.ecotravel.back.exception.AuthenticationException;
import be.ecotravel.back.repository.UserRepository;
import be.ecotravel.back.repository.UserRoleRepository;
import be.ecotravel.back.user.dto.LoginUserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRoleRepository userRoleRepository;
    private final JwtService jwtService;
    private final EmailService emailService;
    @Value("${ecotravel.apiurl}")
    private String apiUrl;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            UserRoleRepository userRoleRepository,
            EmailService emailService,
            JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.jwtService = jwtService;
        this.emailService = emailService;
    }

    public User authenticate(LoginUserDto input) throws AuthenticationException {
        Optional<User> user = userRepository.findByEmail(input.email());

        if (user.isPresent()) {
            if(!user.get().isActivated()){
                throw new AuthenticationException("Email address is not verificated");
            }
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                input.email(),
                                input.password()
                        )
                );

                return user.get();
            } catch (Exception exception) {
                throw new AuthenticationException("Invalid email or password");
            }
        } else {
            throw new AuthenticationException("There is no account with this email address");
        }
    }

    public void verifyEmail(String token) throws AuthenticationException {
        String id = jwtService.extractUsername(token);
        Optional<User> user = userRepository.findUserById(UUID.fromString(id)); //TODO Transformer en else throw

        if(user.isPresent()){
            user.get().setActivated(true);
            userRepository.save(user.get());
            return;
        }

        throw new AuthenticationException("User not found");
    }
}
