package com.example.bankservice.service;

import com.example.bankservice.dto.FIOSearchRequest;
import com.example.bankservice.entity.Client;
import com.example.bankservice.repos.BankAccountRepo;
import com.example.bankservice.repos.ClientRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final BankAccountRepo bankAccountRepo;
    private final ClientRepo clientRepo;

    public SearchService(BankAccountRepo bankAccountRepo, ClientRepo clientRepo) {
        this.bankAccountRepo = bankAccountRepo;
        this.clientRepo = clientRepo;
    }

    public List<Client> searchByBirthdate(String birthdate) {
        LocalDate date = LocalDate.parse(birthdate);
        List<Client> clients = clientRepo.findAll();
       return clients.stream().filter(client -> client.getBirthdate() != null && client.getBirthdate().isAfter(date))
                .collect(Collectors.toList());
    }

    public List<Client> searchByPhone(String phone) {
        List<Client> clients=clientRepo.findAll();
        return clients.stream().filter(client -> client.getPhone()!=null && client.getPhone().contains(phone))
                .collect(Collectors.toList());
    }

    public List<Client> searchByFIO(FIOSearchRequest fioSearchRequest) {
        String name = (fioSearchRequest.getName() != null) ? fioSearchRequest.getName() + "%" : null;
        String surname = (fioSearchRequest.getSurname() != null) ? fioSearchRequest.getSurname() + "%" : null;
        String papaname = (fioSearchRequest.getPapaname() != null) ? fioSearchRequest.getPapaname() + "%" : null;

        return clientRepo.findByFIO(name, surname, papaname);
    }

    public List<Client> searchByEmail(String email) {
        List<Client> clients=clientRepo.findAll();
        return clients.stream().filter(client -> client.getEmail()!=null && client.getPhone().contains(email))
                .collect(Collectors.toList());
    }
}
