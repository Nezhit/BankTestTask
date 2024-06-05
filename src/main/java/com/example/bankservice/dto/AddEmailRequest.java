package com.example.bankservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddEmailRequest {
    @NotBlank(message = "Login is mandatory")
    private String login;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is mandatory")
    private String email;
}
