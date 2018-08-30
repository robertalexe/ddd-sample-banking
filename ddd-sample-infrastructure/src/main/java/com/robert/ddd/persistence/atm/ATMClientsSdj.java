package com.robert.ddd.persistence.atm;

import com.robert.ddd.atm.ATMClient;
import com.robert.ddd.atm.ATMClientId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ATMClientsSdj extends JpaRepository<ATMClient, ATMClientId> {
}
