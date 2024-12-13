package be.ecotravel.back.service;

import be.ecotravel.back.entity.User;
import be.ecotravel.back.entity.UserRole;
import be.ecotravel.back.entity.UserRoleEnum;
import be.ecotravel.back.exception.AuthenticationException;
import be.ecotravel.back.repository.UserRepository;
import be.ecotravel.back.repository.UserRoleRepository;
import be.ecotravel.back.user.dto.UserCreationDto;
import be.ecotravel.back.user.mapper.UserMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final UserRoleRepository userRoleRepo;

    private final UserMapper userMapper;

    @Autowired
    public UserService(
            UserRepository userRepo,
            UserRoleRepository userRoleRepo,
            UserMapper userMapper
    ) {
        this.userRepo = userRepo;
        this.userRoleRepo = userRoleRepo;
        this.userMapper = userMapper;
    }

    public UUID createUser(UserCreationDto userDto, String hashedPassword) {
        UserRole userRole = userRoleRepo.findByName(UserRoleEnum.USER)
                .orElseThrow(() -> new EntityNotFoundException(String.format("The %s role does not exist, therefor the user cannot be added.", UserRoleEnum.USER.name())));

        boolean userExists = userRepo.findByEmail(userDto.email()).isPresent();

        if (userExists) {
            throw new EntityExistsException("This email is already used");
        }

        User user = userMapper.toEntity(userDto, hashedPassword, userRole);
        userRepo.save(user);

        return user.getId();
    }

    public UUID getUserId(String email) {
        User user = getUserWithEmail(email);
        return user.getId();
    }

    public boolean isUserVerified(UUID userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(EntityNotFoundException::new); // On donne pas d'info pour des raisons de sécurité

        return user.isActivated();
    }

    public void activateUser(UUID userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(EntityNotFoundException::new); // On donne pas d'info pour des raisons de sécurité

        user.setActivated(true);
        userRepo.save(user);
    }

    private User getUserWithEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Could not find the user with the email: " + email));
    }
}
