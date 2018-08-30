package com.robert.ddd.exchange;

import com.robert.ddd.DDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@DDD.ApplicationService
@Service
public class ConsultEURToRONExchangeRate {

    private EURToRONExchangeRates exchangeRates;

    public ConsultEURToRONExchangeRate(EURToRONExchangeRates exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    public BigDecimal getEURToRONExchangeRate() {
        return exchangeRates.getEURToRONExchangeRate();
    }
}