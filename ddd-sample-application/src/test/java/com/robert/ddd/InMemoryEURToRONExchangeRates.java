package com.robert.ddd;

import com.robert.ddd.exchange.EURToRONExchangeRates;

import java.math.BigDecimal;

public class InMemoryEURToRONExchangeRates implements EURToRONExchangeRates {
    @Override
    public BigDecimal getEURToRONExchangeRate() {
        return new BigDecimal("4.65");
    }
}
