package com.robert.ddd.rest.exchange;

import com.robert.ddd.DDD;
import com.robert.ddd.exchange.EURToRONExchangeRates;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@DDD.InfrastructureServiceImpl
@Service
@Profile("!HARDCODED")
public class RestClientEURToRONExchangeRate implements EURToRONExchangeRates {

    private static final String APIURL = "https://free.currencyconverterapi.com/api/v6/convert?q=EUR_RON&compact=y";

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public BigDecimal getEURToRONExchangeRate() {
        ResponseEntity<ExchangeRateRepresentation> response = restTemplate.getForEntity(APIURL, ExchangeRateRepresentation.class);
        return new BigDecimal(response.getBody().getEURRON().val);
    }
}




