package com.artexplorer.proiectpad.controller;

import com.artexplorer.proiectpad.model.User;
import com.artexplorer.proiectpad.model.UserInfo;
import com.artexplorer.proiectpad.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/login")
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping
    public HttpEntity<UserInfo> login()
    {
        // Get the logged in user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        UserInfo user = ((User)loginService.login(username)).getUserInfo();
        return new ResponseEntity<>(user, HttpStatus.OK);

    }
}
