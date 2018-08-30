package com.robert.ddd.consult;

import com.robert.ddd.DDD;
import com.robert.ddd.ObjectNotFoundException;
import com.robert.ddd.atm.ATMClient;
import com.robert.ddd.atm.ATMClientId;
import com.robert.ddd.atm.ATMClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DDD.ApplicationService
@Service
@Transactional
public class ConsultAtmClient {

    private ATMClients atmClients;

    public ConsultAtmClient(ATMClients atmClients) {
        this.atmClients = atmClients;
    }

    public ATMClient viewClient(ATMClientId atmClientId) {
        return atmClients.get(atmClientId).orElseThrow(ObjectNotFoundException::new);

    }
}
