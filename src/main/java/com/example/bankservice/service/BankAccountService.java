package com.example.bankservice.service;

import com.example.bankservice.dto.TransferRequest;
import com.example.bankservice.entity.BankAccount;
import com.example.bankservice.entity.Client;
import com.example.bankservice.exceptions.EntityNotFoundException;
import com.example.bankservice.exceptions.NotEnoughMoneyException;
import com.example.bankservice.repos.BankAccountRepo;
import com.example.bankservice.repos.ClientRepo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Slf4j
@Service
public class BankAccountService {
    private final BankAccountRepo bankAccountRepo;
    private final ClientRepo clientRepo;

    public BankAccountService(BankAccountRepo bankAccountRepo, ClientRepo clientRepo) {
        this.bankAccountRepo = bankAccountRepo;
        this.clientRepo = clientRepo;
    }
    @Scheduled(fixedRate = 60000)
    @Transactional
    public synchronized void increaseMoney(){
        List<BankAccount> bankAccounts = bankAccountRepo.findAll();
        log.info("Запуск фоновой задачи по увеличению средств на счетах");
        for (BankAccount account : bankAccounts) {
            if (account.getMoney()*1.05>=account.getDeposite()*2.07){
                log.info("У аккаунта {} превышен лимит начисления",account.getId());
                continue;
            }
            double newMoney = account.getMoney() * 1.05;
            BigDecimal roundedMoney = new BigDecimal(newMoney).setScale(2, RoundingMode.HALF_UP);
            account.setMoney(roundedMoney.doubleValue());
            log.debug("Увеличение средств на счете {}. Новый баланс: {}", account.getId(), newMoney);
        }
        bankAccountRepo.saveAll(bankAccounts);
    }
    @Transactional
    public synchronized  double transferMoney(TransferRequest transferRequest) {
        Client sender = clientRepo.findByLogin(transferRequest.getLoginSender())
                .orElseThrow(() -> new EntityNotFoundException("Клиент-отправитель не найден"));
        Client receiver = clientRepo.findByLogin(transferRequest.getLoginReceiver())
                .orElseThrow(() -> new EntityNotFoundException("Клиент-получатель не найден"));
        if (transferRequest.getMoney() > sender.getBankAccount().getMoney()) {
            throw new NotEnoughMoneyException("Недостаточно средств");
        }
        double newMoney =sender.getBankAccount().getMoney() - transferRequest.getMoney();
        BigDecimal roundedMoney = new BigDecimal(newMoney).setScale(2, RoundingMode.HALF_UP);
        log.info("Инициирован перевод средств со счета клиента {} (логин: {}) на счет клиента {} (логин: {}) на сумму: {}",
                sender.getId(), sender.getLogin(), receiver.getId(), receiver.getLogin(), transferRequest.getMoney());

        sender.getBankAccount().setMoney(roundedMoney.doubleValue());
        clientRepo.save(sender);
        receiver.getBankAccount().setMoney(receiver.getBankAccount().getMoney() + transferRequest.getMoney());
        clientRepo.save(receiver);
        log.info("Перевод средств успешно выполнен. Новый баланс счета отправителя: {}", sender.getBankAccount().getMoney());
        return sender.getBankAccount().getMoney();

    }
}
