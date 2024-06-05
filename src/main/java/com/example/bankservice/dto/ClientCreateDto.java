package com.example.bankservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ClientCreateDto {
    private Long id;
    @NotBlank(message = "Login is mandatory")
    private String login;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Surname is mandatory")
    private String surname;

    @NotBlank(message = "Papaname is mandatory")
    private String papaname;

    @Pattern(regexp = "^\\+?\\d{10,15}$", message = "Invalid phone number")
    private String phone;

    @Email(message = "Invalid email address")
    private String email;

    @Min(value = 0, message = "Initial money should be at least 0")
    private double money;

    @Past(message = "Birthdate must be in the past")
    private LocalDate birthdate;
}
