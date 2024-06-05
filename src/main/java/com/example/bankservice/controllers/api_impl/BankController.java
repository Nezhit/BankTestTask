package com.example.bankservice.controllers.api_impl;

import com.example.bankservice.controllers.BankApi;
import com.example.bankservice.dto.TransferRequest;
import com.example.bankservice.service.BankAccountService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController implements BankApi {
    private final BankAccountService bankAccountService;

    public BankController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @Override
    public double transferMoney(TransferRequest transferRequest) {
        return bankAccountService.transferMoney(transferRequest);
    }
}
