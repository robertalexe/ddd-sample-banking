package com.robert.ddd.persistence.atm;

import com.robert.ddd.DDD;
import com.robert.ddd.atm.ATMClient;
import com.robert.ddd.atm.ATMClientId;
import com.robert.ddd.atm.ATMClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@DDD.RepositoryImpl
@Repository
public class SdjATMClients implements ATMClients {

    @Autowired
    private ATMClientsSdj sdj;

    @Override
    public Optional<ATMClient> get(ATMClientId id) {
        return Optional.ofNullable(sdj.findOne(id));
    }

    @Override
    public ATMClient add(ATMClient atmClient) {
        return sdj.saveAndFlush(atmClient);
    }

    @Override
    public Set<ATMClient> findAll() {
        return new HashSet<>(sdj.findAll());
    }
}
