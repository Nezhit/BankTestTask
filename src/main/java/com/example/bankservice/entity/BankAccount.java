package com.example.bankservice.entity;

import com.example.bankservice.repos.BankAccountRepo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double money;
    private double deposite;
    @OneToOne(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    private Client client;
    public BankAccount(double money) {
        this.money = money;
        this.deposite=money;
    }
}
