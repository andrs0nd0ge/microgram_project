package com.microgram.project.util;

import com.microgram.project.dao.UserDao;
import com.microgram.project.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {
    private final UserDao userDao;
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDao.getUserByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User was not found");
        }
        return user.get();
    }
}
