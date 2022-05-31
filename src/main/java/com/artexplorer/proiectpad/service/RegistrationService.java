package com.artexplorer.proiectpad.service;

import com.artexplorer.proiectpad.model.RegistrationRequest;
import com.artexplorer.proiectpad.model.User;
import com.artexplorer.proiectpad.model.enUserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class RegistrationService  {

    private final UserService userService;
    private EmailValidatorService emailValidatorService;

    public UserDetails register(RegistrationRequest request)
    {
        boolean isValid = emailValidatorService.test(request.getEmail());

        if(!isValid)
            throw new IllegalStateException("Email not valid");

        return userService.register(
                new User(
                        request.getUsername(),
                        request.getPassword(),
                        request.getEmail(),
                        enUserRole.USER
                )
        );
    }
}