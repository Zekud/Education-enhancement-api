package com.example.Edu.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Edu.backend.dto.CreatedResponse;
import com.example.Edu.backend.dto.ErrorResponse;
import com.example.Edu.backend.model.User;
import com.example.Edu.backend.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(new CreatedResponse("User created successfully"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User loggedInUser = userService.login(user.getEmail(), user.getPassword());
        if (loggedInUser != null) {
            loggedInUser.setPassword(null); // Exclude password from the response
            return ResponseEntity.status(HttpStatus.OK).body(loggedInUser);
        }
        return new ResponseEntity<>(new ErrorResponse("Invalid email or password"), HttpStatus.UNAUTHORIZED);
    }
    @GetMapping("/get")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id) {
        User user = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    
}
