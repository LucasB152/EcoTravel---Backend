package be.ecotravel.back.controller;

import be.ecotravel.back.entity.User;
import be.ecotravel.back.repository.UserRepository;
import be.ecotravel.back.user.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/user")
@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        Optional<User> userById = userRepository.findUserById(uuid);

        if (userById.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = userById.get();
        UserResponse response = new UserResponse(user.getFirstname(), user.getLastName(), user.getUsername(), user.getProfilePicturePath());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
