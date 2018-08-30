package com.robert.ddd.transaction;

import com.robert.ddd.DDD;
import com.robert.ddd.ObjectNotFoundException;
import com.robert.ddd.account.Account;
import com.robert.ddd.account.AccountId;
import com.robert.ddd.atm.ATMClient;
import com.robert.ddd.atm.ATMClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DDD.ApplicationService
@Service
@Transactional
public class ViewTransactionsForAccount {

    private ATMClients atmClients;

    public ViewTransactionsForAccount(ATMClients atmClients) {
        this.atmClients = atmClients;
    }

    public List<Transaction> viewTransactions(AccountId accountId) {

        ATMClient actualClient = atmClients.get(accountId.getAtmId()).orElseThrow(ObjectNotFoundException::new);

        Account account = actualClient.findAccountByUniqueId(accountId.getUniqueId()).orElseThrow(ObjectNotFoundException::new);

        return account.getTransactions();
    }
}
