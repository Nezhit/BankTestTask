package com.example.bankservice.controllers.api_impl;

import com.example.bankservice.controllers.ClientApi;
import com.example.bankservice.dto.AddEmailRequest;
import com.example.bankservice.dto.AddPhoneRequest;
import com.example.bankservice.dto.ChangeEmailRequest;
import com.example.bankservice.dto.ChangePhoneRequest;
import com.example.bankservice.entity.Client;
import com.example.bankservice.service.ClientService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class ClientController implements ClientApi {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public List<Client> getClients() {
        return null;
    }

    @Override
    public Set<String> deleteEmailFromClient(Long id, String email) {
        return clientService.deleteEmailFromClient(id, email);
    }

    @Override
    public Set<String> deletePhoneFromClient(Long id, String phone) {
        return clientService.deletePhoneFromClient( id,  phone);
    }

    @Override
    public Set<String> changePhoneFromClient(ChangePhoneRequest changePhoneRequest) {
        return clientService.changePhoneFromClient(changePhoneRequest);
    }

    @Override
    public Set<String> changeEmailFromClient(ChangeEmailRequest changeEmailRequest) {
        return clientService.changeEmailFromClient(changeEmailRequest);
    }

    @Override
    public Set<String> addPhone(AddPhoneRequest addPhoneRequest) {
        return clientService.addPhone(addPhoneRequest);
    }

    @Override
    public Set<String> addEmail(AddEmailRequest addEmailRequest) {
        return clientService.addEmail(addEmailRequest);
    }
}
