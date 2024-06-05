package com.example.bankservice.dto;

import com.example.bankservice.entity.Client;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ClientRsDto {
    private Long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String papaname;
    private Set<String> phone;
    private Set<String> email;

    public ClientRsDto(Client client) {
        this.login = client.getLogin();
        this.password = client.getPassword();
        this.name = client.getName();
        this.surname = client.getSurname();
        this.papaname = client.getPapaname();
        this.phone = client.getPhone();
        this.email = client.getEmail();
    }
}
