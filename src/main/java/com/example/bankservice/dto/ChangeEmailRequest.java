package com.example.bankservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeEmailRequest {
    @NotBlank(message = "Login is mandatory")
    private String login;

    @Email(message = "Invalid old email address")
    @NotBlank(message = "Old email is mandatory")
    private String oldEmail;

    @Email(message = "Invalid new email address")
    @NotBlank(message = "New email is mandatory")
    private String newEmail;
}
