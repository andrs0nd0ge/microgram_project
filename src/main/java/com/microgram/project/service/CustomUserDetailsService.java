package com.microgram.project.service;

import com.microgram.project.dao.UserDao;
import com.microgram.project.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserDao userDao;

    public CustomUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userDao.getUserByEmail(email);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return userOptional.get();
    }
}
