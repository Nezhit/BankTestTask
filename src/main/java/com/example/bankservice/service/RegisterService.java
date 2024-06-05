package com.example.bankservice.service;

import com.example.bankservice.dto.ClientCreateDto;
import com.example.bankservice.dto.ClientRsDto;
import com.example.bankservice.entity.BankAccount;
import com.example.bankservice.entity.Client;
import com.example.bankservice.repos.BankAccountRepo;
import com.example.bankservice.repos.ClientRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class RegisterService {
    private final ClientRepo clientRepo;
    private final PasswordEncoder passwordEncoder;
    private final BankAccountRepo bankAccountRepo;

    public RegisterService(ClientRepo clientRepo, PasswordEncoder passwordEncoder, BankAccountRepo bankAccountRepo) {
        this.clientRepo = clientRepo;
        this.passwordEncoder = passwordEncoder;
        this.bankAccountRepo = bankAccountRepo;
    }

    public ClientRsDto registerClient(ClientCreateDto clientCreateDto) {
        log.info("Регистрация нового клиента. Данные клиента: {}", clientCreateDto);
        Client client= new Client(clientCreateDto);
        client.setPassword(passwordEncoder.encode(clientCreateDto.getPassword()));
        BankAccount bankAccount=new BankAccount(clientCreateDto.getMoney());
        client.setBankAccount(bankAccount);
        bankAccountRepo.save(bankAccount);
        return new ClientRsDto(clientRepo.save(client));
    }
}
