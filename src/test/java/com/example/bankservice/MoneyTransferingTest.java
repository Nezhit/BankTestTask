package com.example.bankservice;

import com.example.bankservice.config.SpringBootApplicationTest;
import com.example.bankservice.dto.ClientCreateDto;
import com.example.bankservice.dto.LoginRequest;
import com.example.bankservice.dto.TransferRequest;
import com.example.bankservice.entity.Client;
import com.example.bankservice.repos.BankAccountRepo;
import com.example.bankservice.repos.ClientRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MoneyTransferingTest extends SpringBootApplicationTest {

    private String jwtToken;
    @Autowired
    private ClientRepo clientRepo;

    @BeforeEach
    @Transactional
    public void setUp() {
        if (!clientRepo.existsByLogin("testUser")) {
            registerTestUser();
        }
        if (!clientRepo.existsByLogin("testReceiver")) {
            registerTestReceiver();
        }
        jwtToken = authenticateAndGetJwtToken();
    }

    private void registerTestUser() {
        ClientCreateDto clientCreateDto = new ClientCreateDto();
        clientCreateDto.setMoney(200);
        clientCreateDto.setName("Ivan");
        clientCreateDto.setSurname("Ivanov");
        clientCreateDto.setPapaname("Ivanovich");
        clientCreateDto.setBirthdate(LocalDate.ofYearDay(2003, 123));
        clientCreateDto.setEmail("login@mail.ru");
        clientCreateDto.setLogin("testUser");
        clientCreateDto.setPassword("testPassword");
        clientCreateDto.setPhone("12341231231");
        webTestClient
                .post()
                .uri("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(clientCreateDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.login").isEqualTo("testUser");
    }

    private void registerTestReceiver() {
        ClientCreateDto clientCreateDto2 = new ClientCreateDto();
        clientCreateDto2.setMoney(200);
        clientCreateDto2.setName("Ivan");
        clientCreateDto2.setSurname("Ivanov");
        clientCreateDto2.setPapaname("Ivanovich");
        clientCreateDto2.setBirthdate(LocalDate.ofYearDay(2003, 123));
        clientCreateDto2.setEmail("login@mail.ru");
        clientCreateDto2.setLogin("testReceiver");
        clientCreateDto2.setPassword("testPassword");
        clientCreateDto2.setPhone("12341231231");
        webTestClient
                .post()
                .uri("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(clientCreateDto2)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.login").isEqualTo("testReceiver");
    }

    private String authenticateAndGetJwtToken() {
        return webTestClient
                .post()
                .uri("/api/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new LoginRequest("testUser", "testPassword"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
    }

    @Test
    public void testSuccessfulMoneyTransfer() {
        System.out.println("JWT = " + jwtToken);
        TransferRequest transferRequest = new TransferRequest("testUser", "testReceiver", 20.0);

        webTestClient
                .post()
                .uri("/api/bank/transfer")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(transferRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Double.class)
                .value(balance -> assertTrue(balance > 0, "Balance should be greater than 0"));
    }

    @Test
    public void testUnsuccessfulMoneyTransfer_InsufficientFunds() {
        TransferRequest transferRequest = new TransferRequest("testUser", "testReceiver", 10000.0);

        webTestClient
                .post()
                .uri("/api/bank/transfer")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(transferRequest)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.error").isEqualTo("Недостаточно средств");
    }

    @Test
    public void testUnsuccessfulMoneyTransfer_Unauthorized() {
        TransferRequest transferRequest = new TransferRequest("testUser", "testReceiver", 100.0);

        webTestClient
                .post()
                .uri("/api/bank/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(transferRequest)
                .exchange()
                .expectStatus().isForbidden();
    }
}
