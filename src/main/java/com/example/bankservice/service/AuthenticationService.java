package com.example.bankservice.service;

import com.example.bankservice.dto.ClientRsDto;
import com.example.bankservice.dto.LoginRequest;
import com.example.bankservice.entity.Client;
import com.example.bankservice.repos.ClientRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    private final ClientRepo clientRepo;

    public AuthenticationService(PasswordEncoder passwordEncoder, JwtService jwtService,
                                 AuthenticationManager authenticationManager,
                                 ClientRepo clientRepo) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.clientRepo = clientRepo;
    }
    public String authenticate(LoginRequest loginRequest){
        log.info("Попытка авторизации клиента с логином {}", loginRequest.getLogin());
        authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(
                        loginRequest.getLogin(),
                loginRequest.getPassword()));
        Client client=clientRepo.findByLogin(loginRequest.getLogin()).get();
        String jwtToken = jwtService.generateToken(client);
        log.info("Клиент {} успешно авторизован", loginRequest.getLogin());
        return jwtToken;
    }
}
