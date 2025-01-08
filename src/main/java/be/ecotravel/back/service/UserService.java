package be.ecotravel.back.service;

import be.ecotravel.back.entity.User;
import be.ecotravel.back.entity.UserRole;
import be.ecotravel.back.entity.UserRoleEnum;
import be.ecotravel.back.exception.AuthenticationException;
import be.ecotravel.back.repository.UserRepository;
import be.ecotravel.back.repository.UserRoleRepository;
import be.ecotravel.back.user.dto.UserCreationDto;
import be.ecotravel.back.user.dto.UserPasswordModificationDto;
import be.ecotravel.back.user.dto.UserRoleDto;
import be.ecotravel.back.user.mapper.UserMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import be.ecotravel.back.user.dto.UserResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    public final CloudinaryService cloudinaryService;

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepo;

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(
            UserRepository userRepo,
            UserRoleRepository userRoleRepo,
            UserMapper userMapper,
            CloudinaryService cloudinaryService,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepo;
        this.userRoleRepo = userRoleRepo;
        this.userMapper = userMapper;
        this.cloudinaryService = cloudinaryService;
        this.passwordEncoder = passwordEncoder;
    }

    public UUID createUser(UserCreationDto userDto, String hashedPassword) {
        UserRole userRole = userRoleRepo.findByName(UserRoleEnum.USER)
                .orElseThrow(() -> new EntityNotFoundException(String.format("The %s role does not exist, therefor the user cannot be added.", UserRoleEnum.USER.name())));

        boolean userExists = userRepository.findByEmail(userDto.email()).isPresent();

        if (userExists) {
            throw new EntityExistsException("This email is already used");
        }

        User user = userMapper.toEntity(userDto, hashedPassword, userRole);
        userRepository.save(user);

        return user.getId();
    }

    public UUID getUserId(String email) {
        User user = getUserWithEmail(email);
        return user.getId();
    }

    public boolean isUserVerified(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new); // On donne pas d'info pour des raisons de sécurité

        return user.isActivated();
    }

    public void activateUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new); // On donne pas d'info pour des raisons de sécurité

        user.setActivated(true);
        userRepository.save(user);
    }

    private User getUserWithEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Could not find the user with the email: " + email));

    }

    public UserResponse getUserById(String id) throws EntityNotFoundException {
        User user = findUserById(id);
        return userMapper.toResponse(user);
    }

    private User findUserById(String id) throws EntityNotFoundException {
        UUID uuid = UUID.fromString(id);

        return userRepository.findUserById(uuid)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<UserRoleDto> getAllUsers(){
        return userRepository.findAll().stream().map(
                this.userMapper::toUserRole
        ).collect(Collectors.toList());
    }

    public UserResponse putUserById(String id, UserCreationDto registerUserDto) { //TODO Changer ça avec le front pour avoir une bonne dto distincte et pas garder celle de la creation
        User user = findUserById(id);
        Optional<User> userFromDb = userRepository.findByEmail(registerUserDto.email());

        if (registerUserDto.email() != null) { //TODO Faire en sorte que les annotations des dtos puissent fonctionner directement pour améliorer l'expension de l'app
            if(userFromDb.isEmpty() || userFromDb.get() == user){
                user.setEmail(registerUserDto.email());
            }else{
                throw new EntityExistsException("This email is already linked to an account");
            }
        }

        if (registerUserDto.firstName() != null) {
            user.setFirstName(registerUserDto.firstName());
        }

        if (registerUserDto.lastName() != null) {
            user.setLastName(registerUserDto.lastName());
        }

        userRepository.save(user);
        return userMapper.toResponse(user);
    }

    public UserResponse addProfilePicture(String id, MultipartFile file) {
        String imageUrl;

        try {
            imageUrl = cloudinaryService.uploadFileToFolder(file, "userPicture", id+"_"+ System.currentTimeMillis());
        } catch (IOException e) {
            throw new RuntimeException(e); //TODO gérer l'exception pour retourner au front
        }

        User user = findUserById(id);
        if (user.getProfilePicturePath() != null) {
            cloudinaryService.deleteImageByUrl(user.getProfilePicturePath());
        }

        user.setProfilePicturePath(imageUrl);
        userRepository.save(user);

        return userMapper.toResponse(user);
    }

    public void modifyPassword(UserPasswordModificationDto userDto) {
        User user = findUserById(userDto.userId());

        if (!passwordEncoder.matches(userDto.currentPassword(), user.getPassword())) {
            throw new AuthenticationException("Password is not correct");
        }

        user.setPassword(passwordEncoder.encode(userDto.newPassword()));
        userRepository.save(user);
    }

    public void deleteUserById(String id) {
        userRepository.deleteById(UUID.fromString(id));
    }

    public void promoteToAdmin(String userId) {
        User user = findUserById(userId);

        UserRole userRole = userRoleRepo.findByName(UserRoleEnum.ADMIN).orElseThrow();

        user.setUserRole(userRole);
        userRepository.save(user);
    }
}