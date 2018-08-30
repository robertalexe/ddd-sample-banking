package com.robert.ddd.atm;

import com.robert.ddd.DDD;

import java.util.Optional;
import java.util.Set;

@DDD.Repository
public interface ATMClients {

    Optional<ATMClient> get(ATMClientId id);

    ATMClient add(ATMClient atmClient);

    Set<ATMClient> findAll();
}
