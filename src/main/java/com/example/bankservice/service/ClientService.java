package com.example.bankservice.service;

import com.example.bankservice.dto.AddEmailRequest;
import com.example.bankservice.dto.AddPhoneRequest;
import com.example.bankservice.dto.ChangeEmailRequest;
import com.example.bankservice.dto.ChangePhoneRequest;
import com.example.bankservice.entity.Client;
import com.example.bankservice.exceptions.AlreadyInUseException;
import com.example.bankservice.exceptions.EntityNotFoundException;
import com.example.bankservice.repos.ClientRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Slf4j
@Service
public class ClientService {
    private final ClientRepo clientRepo;

    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public Set<String> deleteEmailFromClient(Long id, String email) {
        Client client= clientRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Клиент не найден"));
        if(client.getEmail().size()<=1){
            return client.getEmail();
        }
        client.getEmail().remove(email);
        clientRepo.save(client);
        return client.getEmail();
    }
    public Set<String> deletePhoneFromClient(Long id, String phone){
        Client client= clientRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Клиент не найден"));
        if(client.getPhone().size()<=1){
            return client.getPhone();
        }
        client.getEmail().remove(phone);
        clientRepo.save(client);
        return client.getPhone();
    }

    public Set<String> changePhoneFromClient(ChangePhoneRequest changePhoneRequest) {
        Client client = clientRepo.findByLogin(changePhoneRequest.getLogin()).orElseThrow(() -> new EntityNotFoundException("Клиент не найден"));
        List<Client> clients = clientRepo.findAll();
       Set<String> phones = clients.stream()
               .flatMap(cl -> client.getPhone().stream())
               .collect(Collectors.toSet());
       if(phones.contains(changePhoneRequest.getNewPhone())){
           log.warn("Новый телефон {} уже занят другим клиентом", changePhoneRequest.getNewPhone());
           throw new AlreadyInUseException("Телефон уже занят");
       }
       client.getPhone().remove(changePhoneRequest.getOldPhone());
       client.getPhone().add(changePhoneRequest.getNewPhone());
       return client.getPhone();
    }

    public Set<String> changeEmailFromClient(ChangeEmailRequest changeEmailRequest) {
        Client client = clientRepo.findByLogin(changeEmailRequest.getLogin()).orElseThrow(() -> new EntityNotFoundException("Клиент не найден"));
        List<Client> clients = clientRepo.findAll();
        Set<String> emails = clients.stream()
                .flatMap(cl -> client.getEmail().stream())
                .collect(Collectors.toSet());
        if(emails.contains(changeEmailRequest.getNewEmail())){
            log.warn("Новый email {} уже занят другим клиентом", changeEmailRequest.getNewEmail());
            throw new AlreadyInUseException("Email уже занят");
        }
        client.getEmail().remove(changeEmailRequest.getOldEmail());
        client.getEmail().add(changeEmailRequest.getNewEmail());
        return client.getEmail();
    }

    public Set<String> addPhone(AddPhoneRequest addPhoneRequest) {
        Client client = clientRepo.findByLogin(addPhoneRequest.getLogin()).orElseThrow(() -> new EntityNotFoundException("Клиент не найден"));
        List<Client> clients = clientRepo.findAll();
        Set<String> phones = clients.stream()
                .flatMap(cl -> client.getPhone().stream())
                .collect(Collectors.toSet());
        if(phones.contains(addPhoneRequest.getPhone())){
            log.warn("Новый телефон {} уже занят другим клиентом", addPhoneRequest.getPhone());
            throw new AlreadyInUseException("Телефон уже занят");
        }
        client.getPhone().add(addPhoneRequest.getPhone());
        return client.getPhone();

    }

    public Set<String> addEmail(AddEmailRequest addEmailRequest) {
        Client client = clientRepo.findByLogin(addEmailRequest.getLogin()).orElseThrow(() -> new EntityNotFoundException("Клиент не найден"));
        List<Client> clients = clientRepo.findAll();
        Set<String> emails = clients.stream()
                .flatMap(cl -> client.getEmail().stream())
                .collect(Collectors.toSet());
        if(emails.contains(addEmailRequest.getEmail())){
            log.warn("Новый email {} уже занят другим клиентом", addEmailRequest.getEmail());
            throw new AlreadyInUseException("Email уже занят");
        }
        client.getEmail().add(addEmailRequest.getEmail());
        return client.getEmail();
    }
}
