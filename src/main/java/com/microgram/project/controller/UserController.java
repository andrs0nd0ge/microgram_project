package com.microgram.project.controller;

import com.microgram.project.dto.UserDto;
import com.microgram.project.service.UserService;
import com.microgram.project.util.UtilityClass;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UtilityClass util;
    private final UserService userService;
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/name/{name}")
    public List<UserDto> getUsersByName(@PathVariable String name) {
        return userService.getUsersByName(name.toLowerCase().trim());
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
        UserDto user = userService.getUserByUsername(username);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/check_mail/{email}")
    public ResponseEntity<String> userExistsByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.findUserByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/check_username/{username}")
    public ResponseEntity<String> userExistsByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.findUserByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/create")
    public ResponseEntity<String> createUsersTable() {
        return new ResponseEntity<>(util.createUsersTable(), HttpStatus.OK);
    }

    @GetMapping("/insert")
    public ResponseEntity<String> insertIntoUsersTable() {
        return new ResponseEntity<>(util.insertIntoUsers(), HttpStatus.OK);
    }
}
