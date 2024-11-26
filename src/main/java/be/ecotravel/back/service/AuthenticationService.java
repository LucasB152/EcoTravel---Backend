package be.ecotravel.back.service;

import be.ecotravel.back.entity.User;
import be.ecotravel.back.entity.UserRole;
import be.ecotravel.back.repository.UserRepository;
import be.ecotravel.back.repository.UserRoleRepository;
import be.ecotravel.back.user.dto.LoginUserDto;
import be.ecotravel.back.user.dto.UserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final UserRoleRepository userRoleRepository;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            UserRoleRepository userRoleRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    public User signup(UserDto input) {
        Optional<UserRole> userRoleOptional = userRoleRepository.findByName("USER");
        if (userRoleOptional.isEmpty()) {
            throw new IllegalArgumentException("Role not found: user");
        }
        User user = new User();
        user.setFirstname(input.firstname());
        user.setLastName(input.lastname());
        user.setEmail(input.email());
        user.setPassword(passwordEncoder.encode(input.password()));
        user.setUserRole(userRoleOptional.get());

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.email(),
                        input.password()
                )
        );

        return userRepository.findByEmail(input.email())
                .orElseThrow();
    }
}
