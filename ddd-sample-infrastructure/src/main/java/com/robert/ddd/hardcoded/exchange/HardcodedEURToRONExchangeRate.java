package com.robert.ddd.hardcoded.exchange;

import com.robert.ddd.DDD;
import com.robert.ddd.exchange.EURToRONExchangeRates;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@DDD.InfrastructureServiceImpl
@Service
@Profile("HARDCODED")
public class HardcodedEURToRONExchangeRate implements EURToRONExchangeRates {

    private static final BigDecimal HARDCODED_RATE = new BigDecimal("4.65");

    @Override
    public BigDecimal getEURToRONExchangeRate() {
        return HARDCODED_RATE;
    }
}