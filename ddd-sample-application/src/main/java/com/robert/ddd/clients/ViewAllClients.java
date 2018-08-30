package com.robert.ddd.clients;

import com.robert.ddd.DDD;
import com.robert.ddd.atm.ATMClient;
import com.robert.ddd.atm.ATMClients;
import com.robert.ddd.atm.IdentificationInformation;
import com.robert.ddd.client.Client;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@DDD.ApplicationService
@Service
@Transactional
public class ViewAllClients {

    private ATMClients atmClients;

    public ViewAllClients(ATMClients atmClients) {
        this.atmClients = atmClients;
    }

    public Set<Client> viewAllClients() {
        return atmClients.findAll().stream().map(ATMClient::getIdentificationInformation).map(IdentificationInformation::getClient).collect(Collectors.toSet());
    }
}
