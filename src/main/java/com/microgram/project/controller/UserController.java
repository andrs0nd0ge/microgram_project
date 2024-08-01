package com.microgram.project.controller;

import com.microgram.project.dto.RegistrationDto;
import com.microgram.project.dto.UserDto;
import com.microgram.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<UserDto>> getUsersByName(@PathVariable String name) {
        List<UserDto> users = userService.getUsersByName(name.toLowerCase().trim());
        if (users.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        UserDto user = userService.getUserByEmail(email.toLowerCase().trim());
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        UserDto user = userService.getUserByUsername(username.toLowerCase().trim());
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/check_mail/{email}")
    public ResponseEntity<Boolean> userExistsByEmail(@PathVariable String email) {
        boolean userWasFound = userService.findUserByEmail(email);

        if (userWasFound) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }

        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/check_username/{username}")
    public ResponseEntity<Boolean> userExistsByUsername(@PathVariable String username) {
        boolean userWasFound = userService.findUserByUsername(username);

        if (userWasFound) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }

        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody RegistrationDto registrationDto) {
        userService.registerUser(registrationDto);
    }
}
