package com.microgram.project.service;

import com.microgram.project.dao.UserDao;
import com.microgram.project.dto.UserDto;
import com.microgram.project.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    public List<UserDto> getAllUsers() {
        List<User> users = userDao.getAllUsers();
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }

    public List<UserDto> getUsersByName(String name) {
        List<User> users = userDao.getUsersByName(name);
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }

    public UserDto getUserByEmail(String email) {
        User user = userDao.getUserByEmail(email).orElse(null);
        if (user != null) {
            return UserDto.from(user);
        }
        return null;
    }

    public UserDto getUserByUsername(String username) {
        User user = userDao.getUserByUsername(username).orElse(null);
        if (user != null) {
            return UserDto.from(user);
        }
        return null;
    }

    public String findUserByEmail(String email) {
        User user = userDao.checkIfUserExistsByEmail(email).orElse(null);
        if (user != null) {
            return "User was found";
        }
        return "User was not found";
    }

    public String findUserByUsername(String username) {
        User user = userDao.checkIfUserExistsByUsername(username).orElse(null);
        if (user != null) {
            return "User was found";
        }
        return "User was not found";
    }

    public void registerUser(String name, String username, String email, String password) {
        List<User> users = userDao.getAllUsers();
        for (User user : users) {
            if (user.getUsername().equals(username) || user.getEmail().equals(email)) {
                System.out.println("User already exists");
            } else {
                password = passwordEncoder.encode(password);
                userDao.registerUser(name, username, email, password);
                System.out.println("User was registered successfully");
            }
            break;
        }
    }
}
