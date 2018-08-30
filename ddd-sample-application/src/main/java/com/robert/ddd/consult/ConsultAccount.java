package com.robert.ddd.consult;

import com.robert.ddd.DDD;
import com.robert.ddd.ObjectNotFoundException;
import com.robert.ddd.account.Account;
import com.robert.ddd.account.AccountId;
import com.robert.ddd.atm.ATMClient;
import com.robert.ddd.atm.ATMClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DDD.ApplicationService
@Service
@Transactional
public class ConsultAccount {

    private ATMClients atmClients;

    public ConsultAccount(ATMClients atmClients) {
        this.atmClients = atmClients;
    }

    public Account viewAccount(AccountId accountId) {
        ATMClient currentClient = atmClients.get(accountId.getAtmId()).orElseThrow(ObjectNotFoundException::new);

        return currentClient.findAccountByUniqueId(accountId.getUniqueId()).orElse(null);
    }
}
