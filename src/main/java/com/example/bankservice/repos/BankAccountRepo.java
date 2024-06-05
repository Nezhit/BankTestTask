package com.example.bankservice.repos;

import com.example.bankservice.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepo extends JpaRepository<BankAccount,Long> {
}
