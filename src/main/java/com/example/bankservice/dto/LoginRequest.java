package com.example.bankservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoginRequest {
    @NotBlank(message = "Login is mandatory")
    private String login;

    @NotBlank(message = "Password is mandatory")
    private String password;

    public LoginRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
