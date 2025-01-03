package be.ecotravel.back.controller;

import be.ecotravel.back.entity.User;
import be.ecotravel.back.service.UserService;
import be.ecotravel.back.user.dto.UserCreationDto;
import be.ecotravel.back.user.dto.UserPasswordModificationDto;
import be.ecotravel.back.user.dto.UserResponse;
import be.ecotravel.back.user.dto.UserRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RequestMapping("api/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserRoleDto>> getAllUsers(){
        List<UserRoleDto> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        UserResponse response = userService.getUserById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable String id){
        userService.deleteUserById(id);
        return ResponseEntity.ok(Map.of("Message", "Votre compte à bien été supprimé"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putUserById(@PathVariable String id, @RequestBody UserCreationDto userDto) {
        userService.putUserById(id, userDto);
        return ResponseEntity.ok(Map.of("Message", "Informations changed with success"));
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
