package com.example.bankservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangePhoneRequest {
    @NotBlank(message = "Login is mandatory")
    private String login;

    @Pattern(regexp = "^\\+?\\d{10,15}$", message = "Invalid old phone number")
    @NotBlank(message = "Old phone is mandatory")
    private String oldPhone;

    @Pattern(regexp = "^\\+?\\d{10,15}$", message = "Invalid new phone number")
    @NotBlank(message = "New phone is mandatory")
    private String newPhone;


}
