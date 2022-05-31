package com.artexplorer.proiectpad.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    private final UserService userService;

    public UserDetails login(String username)
    {
        return userService.loadUserByUsername(username);
    }
}
