package be.ecotravel.back.service;

import be.ecotravel.back.entity.User;
import be.ecotravel.back.entity.UserRole;
import be.ecotravel.back.entity.UserRoleEnum;
import be.ecotravel.back.exception.AuthenticationException;
import be.ecotravel.back.repository.UserRepository;
import be.ecotravel.back.repository.UserRoleRepository;
import be.ecotravel.back.user.dto.UserCreationDto;
import be.ecotravel.back.user.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Value("${ecotravel.apiurl}")
    private String apiUrl;

    private final UserRepository userRepo;
    private final UserRoleRepository userRoleRepo;

    private final EmailService emailService;
    private final JwtService jwtService;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(
            UserRepository userRepo,
            UserRoleRepository userRoleRepo,
            EmailService emailService,
            JwtService jwtService,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepo = userRepo;
        this.userRoleRepo = userRoleRepo;
        this.emailService = emailService;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public String createUser(UserCreationDto userDto) throws AuthenticationException {
        UserRole userRole = userRoleRepo.findByName(UserRoleEnum.USER)
                .orElseThrow(() -> new EntityNotFoundException(String.format("The %s role does not exist, therefor the user cannot be added.", UserRoleEnum.USER.name())));

        boolean userExists = userRepo.findByEmail(userDto.email()).isPresent();

        if (userExists) {
            throw new AuthenticationException("This email is already used");
        }

        String hashedPassword = passwordEncoder.encode(userDto.password());

        User user = userMapper.toEntity(userDto, hashedPassword, userRole);

        userRepo.save(user);

        sendEmailConfirmation(userDto.email());

        return "Confirmation email has been sent at " + user.getEmail();
    }

    private void sendEmailConfirmation(String email) { //TODO Donner ça au service email au lieu de le garder dans UserService ?
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("The email does not exist, therefor the email cannot be sent."));

        String token = jwtService.generateToken(user);
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
                email,
                "Vérifiez votre adresse e-mail",
                emailContent
        );
    }

}
