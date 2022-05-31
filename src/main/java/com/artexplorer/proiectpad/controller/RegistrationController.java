package com.artexplorer.proiectpad.controller;

import com.artexplorer.proiectpad.model.RegistrationRequest;
import com.artexplorer.proiectpad.model.User;
import com.artexplorer.proiectpad.model.UserInfo;
import com.artexplorer.proiectpad.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/register")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public HttpEntity<UserInfo> register(@RequestBody RegistrationRequest request)
    {
        UserInfo user = ((User)registrationService.register(request)).getUserInfo();
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
