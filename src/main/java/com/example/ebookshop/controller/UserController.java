package com.example.ebookshop.controller;

import com.example.ebookshop.dto.LoginRequest;
import com.example.ebookshop.model.User;
import com.example.ebookshop.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // GET /users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //GET /users/user_name
    @GetMapping("/{username}")
    public Optional<User> getUserByName(@PathVariable String username){
        return userRepository.findByUsername(username);
    }

    //POST /users/login
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "User not found"));
        }

        User user = userOptional.get();
        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Wrong password"));
        }

        return ResponseEntity.ok(Map.of("message", "Login successful"));
    }

    // POST /users
    @PostMapping
    public ResponseEntity<Map<String, String>> addUser(@Valid @RequestBody User user) {
        userRepository.save(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User created successfully");
        return ResponseEntity.ok(response);
    }

}
