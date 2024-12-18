package be.ecotravel.back.controller;

import be.ecotravel.back.service.UserService;
import be.ecotravel.back.user.dto.UserCreationDto;
import be.ecotravel.back.user.dto.UserPasswordModificationDto;
import be.ecotravel.back.user.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RequestMapping("api/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        UserResponse response = userService.getUserById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putUserById(@PathVariable String id, @RequestBody UserCreationDto userDto) {
        return ResponseEntity.status(201).body(userService.putUserById(id, userDto));
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<?> putPasswordById(@PathVariable String id, @RequestBody UserPasswordModificationDto userDto) {
        userService.modifyPassword(userDto);
        return ResponseEntity.ok(Map.of("Message", "Password changed with success"));
    }

    @PostMapping("/picture/{id}")
    public ResponseEntity<?> postProfilePicture(@PathVariable String id, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(userService.addProfilePicture(id, file));
    }
}
