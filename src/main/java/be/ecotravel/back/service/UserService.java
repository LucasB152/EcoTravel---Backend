package be.ecotravel.back.service;

import be.ecotravel.back.entity.User;
import be.ecotravel.back.entity.UserRole;
import be.ecotravel.back.entity.UserRoleEnum;
import be.ecotravel.back.repository.UserRepository;
import be.ecotravel.back.repository.UserRoleRepository;
import be.ecotravel.back.user.dto.UserCreationDto;
import be.ecotravel.back.user.mapper.UserMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import be.ecotravel.back.user.dto.UserResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class UserService {

    public final CloudinaryService cloudinaryService;

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepo;

    private final UserMapper userMapper;

    @Autowired
    public UserService(
            UserRepository userRepo,
            UserRoleRepository userRoleRepo,
            UserMapper userMapper,
            CloudinaryService cloudinaryService
    ) {
        this.userRepository = userRepo;
        this.userRoleRepo = userRoleRepo;
        this.userMapper = userMapper;
        this.cloudinaryService = cloudinaryService;
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

    public UserResponse getUserById (String id) throws EntityNotFoundException {
        User user = findUserById(id);
        return new UserResponse(user.getFirstName(), user.getLastName(), user.getUsername(), user.getProfilePicturePath());
    }

    private User findUserById (String id) throws EntityNotFoundException {
        UUID uuid = UUID.fromString(id);

        return userRepository.findUserById(uuid)
                .orElseThrow(EntityNotFoundException::new);
    }

    public UserResponse putUserById (String id, UserCreationDto registerUserDto) {
        User user = findUserById(id);

        if (registerUserDto.email() != null && !registerUserDto.email().equals(user.getEmail())) {
            user.setEmail(registerUserDto.email());
        }

        if (registerUserDto.firstName() != null && !registerUserDto.firstName().equals(user.getFirstName())) {
            user.setFirstName(registerUserDto.firstName());
        }

        if (registerUserDto.lastName() != null && !registerUserDto.lastName().equals(user.getLastName())) {
            user.setLastName(registerUserDto.lastName());
        }

        userRepository.save(user);
        return getUserById(id);
    }

    public UserResponse addProfilePicture (String id, MultipartFile file) throws Exception {
        String imageUrl = cloudinaryService.uploadImageToFolder(file, "userPicture", id);
        User user = findUserById(id);
        if (!user.getProfilePicturePath().equals(imageUrl)) {
            user.setProfilePicturePath(imageUrl);
        }
        userRepository.save(user);
        return getUserById(id);
    }
}