package com.example.bankservice.controllers.api_impl;

import com.example.bankservice.controllers.RegisterApi;
import com.example.bankservice.dto.ClientCreateDto;
import com.example.bankservice.dto.ClientRsDto;
import com.example.bankservice.service.RegisterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController implements RegisterApi {
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @Override
    public ClientRsDto register(ClientCreateDto clientCreateDto) {
        System.out.println(clientCreateDto.toString());
        return registerService.registerClient(clientCreateDto);
    }
}
