package be.ecotravel.back.controller;

import be.ecotravel.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/admin")
@RestController
public class AdminController {
    private final UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/{userId}/role/admin")
    public ResponseEntity<?> addNewAdmin(@PathVariable String userId){
        this.userService.promoteToAdmin(userId);
        return ResponseEntity.ok(this.userService.getAllUsers());
    }
}
