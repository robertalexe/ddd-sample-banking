package com.robert.ddd.withdraw;

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
public class WithdrawFromAccount {

    private ATMClients atmClients;

    public WithdrawFromAccount(ATMClients atmClients) {
        this.atmClients = atmClients;
    }

    public ATMClient withdrawFromAccount(AccountId accountId, BigDecimal amount) {

        ATMClient actualClient = atmClients.get(accountId.getAtmId()).orElseThrow(ObjectNotFoundException::new);
        Account account = actualClient.findAccountByUniqueId(accountId.getUniqueId()).orElseThrow(ObjectNotFoundException::new);

        account.withdrawAmount(amount);

        account.addTransaction(
                new Transaction(
                        new TransactionId(
                                accountId,
                                UUID.randomUUID().toString()
                        ),
                        amount,
                        TransactionType.WITHDRAW,
                        LocalDateTime.now()
                )
        );

        return atmClients.add(actualClient);
    }
}
