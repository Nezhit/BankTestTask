package com.example.bankservice.controllers.api_impl;

import com.example.bankservice.controllers.AuthenticationApi;
import com.example.bankservice.dto.LoginRequest;
import com.example.bankservice.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController implements AuthenticationApi {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public String authClient(LoginRequest loginRequest){
        return authenticationService.authenticate(loginRequest);
    }
}
