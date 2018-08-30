package com.robert.ddd.apirest;

import com.robert.ddd.exchange.ConsultEURToRONExchangeRate;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@CrossOrigin(origins = "*")
public class ExchangeRateResource {

    @Autowired
    private ConsultEURToRONExchangeRate consultExchangeRate;

    @RequestMapping(value = "/consult/exchange-rate", method = RequestMethod.GET)
    @ApiOperation(value = "Consult the EUR to RON Exchange rate. No input needed")
    public BigDecimal consultExchangeRate() {
        return consultExchangeRate.getEURToRONExchangeRate();
    }
}
