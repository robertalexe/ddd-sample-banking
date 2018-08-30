package com.robert.ddd.deposit;

import com.robert.ddd.DDD;
import com.robert.ddd.ObjectNotFoundException;
import com.robert.ddd.account.Account;
import com.robert.ddd.account.AccountId;
import com.robert.ddd.atm.ATMClient;
import com.robert.ddd.atm.ATMClients;
import com.robert.ddd.transaction.Transaction;
import com.robert.ddd.transaction.TransactionId;
import com.robert.ddd.transaction.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@DDD.ApplicationService
@Service
@Transactional
public class DepositInAccount {

    private ATMClients atmClients;

    public DepositInAccount(ATMClients atmClients) {
        this.atmClients = atmClients;
    }

    public ATMClient depositMoneyInAccount(AccountId accountId, BigDecimal amount) {
        ATMClient actualClient = atmClients.get(accountId.getAtmId())
                .orElseThrow(ObjectNotFoundException::new);

        Account account = actualClient.findAccountByUniqueId(accountId.getUniqueId())
                .orElseThrow(ObjectNotFoundException::new);

        account.addAmount(amount);

        account.addTransaction(
                new Transaction(
                        new TransactionId(
                                accountId,
                                UUID.randomUUID().toString()
                        ),
                        amount,
                        TransactionType.DEPOSIT,
                        LocalDateTime.now()
                )
        );

        return atmClients.add(actualClient);
    }
}
