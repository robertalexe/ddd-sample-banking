package com.robert.ddd.transfer;

import com.robert.ddd.DDD;
import com.robert.ddd.ObjectNotFoundException;
import com.robert.ddd.account.Account;
import com.robert.ddd.account.AccountId;
import com.robert.ddd.atm.ATMClient;
import com.robert.ddd.atm.ATMClients;
import com.robert.ddd.transaction.Transaction;
import com.robert.ddd.transaction.TransactionId;
import com.robert.ddd.transaction.TransactionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@DDD.ApplicationService
@Service
@Transactional
public class TransferBetweenAccounts {

    private ATMClients atmClients;

    public TransferBetweenAccounts(ATMClients atmClients) {
        this.atmClients = atmClients;
    }

    public ATMClient transferMoneyBetweenAccounts(AccountId sourceAccountId, AccountId destinationAccountId, BigDecimal amount) {
        ATMClient actualClient = atmClients.get(sourceAccountId.getAtmId()).orElseThrow(ObjectNotFoundException::new);

        Account sourceAccount = actualClient.findAccountByUniqueId(sourceAccountId.getUniqueId()).orElseThrow(ObjectNotFoundException::new);
        Account destinationAccount = actualClient.findAccountByUniqueId(destinationAccountId.getUniqueId()).orElseThrow(ObjectNotFoundException::new);

        sourceAccount.withdrawAmount(amount);
        destinationAccount.addAmount(amount);

        sourceAccount.addTransaction(
                new Transaction(
                        new TransactionId(sourceAccountId, UUID.randomUUID().toString()
                    ),
                    amount,
                    TransactionType.TRANSFER,
                    LocalDateTime.now()
                )
        );

        destinationAccount.addTransaction(
                new Transaction(
                        new TransactionId(destinationAccountId, UUID.randomUUID().toString()
                        ),
                        amount,
                        TransactionType.TRANSFER,
                        LocalDateTime.now()
                )
        );
        atmClients.add(actualClient);
        return actualClient;
    }
}
