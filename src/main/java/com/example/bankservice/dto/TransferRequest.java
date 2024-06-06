package com.example.bankservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransferRequest {
    @NotBlank(message = "Sender login is mandatory")
    private String loginSender;

    @NotBlank(message = "Receiver login is mandatory")
    private String loginReceiver;

    @Min(value = 0, message = "Transfer amount must be positive")
    private double money;
    public TransferRequest(String loginSender, String loginReceiver, double money){
        this.loginReceiver = loginReceiver;
        this.loginSender = loginSender;
        this.money = money;
    }
}
