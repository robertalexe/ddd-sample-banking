package com.robert.ddd.exchange;

import com.robert.ddd.DDD;

import java.math.BigDecimal;

@DDD.InfrastructureService
public interface EURToRONExchangeRates {
    BigDecimal getEURToRONExchangeRate();
}





