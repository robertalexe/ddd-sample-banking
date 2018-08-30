package com.robert.ddd;

import com.robert.ddd.atm.ATMClient;
import com.robert.ddd.atm.ATMClientId;
import com.robert.ddd.atm.ATMClients;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

public class InMemoryATMClients implements ATMClients {

    private Set<ATMClient> clients = new HashSet<>();

    @Override
    public Optional<ATMClient> get(ATMClientId id) {
        return clients.stream().filter(
                client -> client.getId().equals(id)
        ).findFirst();
    }

    @Override
    public ATMClient add(ATMClient atmClient) {
        clients.add(atmClient);
        return atmClient;
    }

    @Override
    public Set<ATMClient> findAll() {
        return unmodifiableSet(clients);
    }
}
