package com.example.bankservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddPhoneRequest {
    @NotBlank(message = "Login is mandatory")
    private String login;

    @Pattern(regexp = "^\\+?\\d{10,15}$", message = "Invalid phone number")
    @NotBlank(message = "Phone is mandatory")
    private String phone;
}
