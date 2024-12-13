package be.ecotravel.back.service;

import be.ecotravel.back.entity.User;
import be.ecotravel.back.entity.UserRole;
import be.ecotravel.back.entity.UserRoleEnum;
import be.ecotravel.back.exception.AuthenticationException;
import be.ecotravel.back.repository.UserRepository;
import be.ecotravel.back.repository.UserRoleRepository;
import be.ecotravel.back.security.JwtService;
import be.ecotravel.back.user.dto.LoginUserDto;
import be.ecotravel.back.user.dto.UserDto;
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

    public String signup(UserDto input) throws AuthenticationException {
        Optional<UserRole> userRoleOptional = userRoleRepository.findByName(UserRoleEnum.USER);

        if (userRoleOptional.isEmpty()) {
            throw new IllegalArgumentException("Role not found: user");
        }

        if (userRepository.findByEmail(input.email()).isEmpty()) {
            User user = new User();
            user.setFirstname(input.firstname());
            user.setLastName(input.lastname());
            user.setEmail(input.email());
            user.setPassword(passwordEncoder.encode(input.password()));
            user.setUserRole(userRoleOptional.get());

            userRepository.save(user);

            User userFromDataBase = sendEmailConfirmation(input.email());

            if(userFromDataBase != null) {
                return "Confirmation email has been sent at " + userFromDataBase.getEmail();
            }else{
                throw new AuthenticationException("User doesn't exist");
            }
        } else {
            throw new AuthenticationException("This email is already used");
        }
    }

    public User sendEmailConfirmation(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) {
            User userFromDataBase = user.get();
            String token = jwtService.generateToken(userFromDataBase);
            String verificationLink = apiUrl + "auth/verify-email/" + token;

            String emailContent = "<html>" +
                    "<body>" +
                    "<p>Bonjour,</p>" +
                    "<p>Merci de vous être inscrit. Veuillez cliquer sur le lien ci-dessous pour vérifier votre adresse e-mail :</p>" +
                    "<p><a href=\"" + verificationLink + "\">Vérifiez votre e-mail</a></p>" +
                    "<p>Si vous ne parvenez pas à cliquer sur le lien, copiez et collez l'URL suivante dans votre navigateur :</p>" +
                    "<p>" + verificationLink + "</p>" +
                    "<p>Merci,</p>" +
                    "<p>L'équipe EcoTravel</p>" +
                    "</body>" +
                    "</html>";

            emailService.sendEmail(
                    "lucasbauduin15@gmail.com",
                    userFromDataBase.getEmail(),
                    "Vérifiez votre adresse e-mail",
                    emailContent
            );
            return userFromDataBase;
        }
        return null;
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
