package be.ecotravel.back.controller;

import be.ecotravel.back.service.CloudinaryService;
import be.ecotravel.back.service.UserService;
import be.ecotravel.back.user.dto.UserDto;
import be.ecotravel.back.user.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("api/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        try {
            UserResponse response = userService.getUserById(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putUserById(@PathVariable String id, @RequestBody UserDto registerUserDto){
        try {
            return ResponseEntity.status(201).body(userService.putUserById(id, registerUserDto));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/picture/{id}")
    public ResponseEntity<?> postProfilePicture(@PathVariable String id, @RequestParam("file") MultipartFile file){
        try {
            return ResponseEntity.ok(userService.addProfilePicture(id, file));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de l'upload : " + e.getMessage());
        }
    }
}
