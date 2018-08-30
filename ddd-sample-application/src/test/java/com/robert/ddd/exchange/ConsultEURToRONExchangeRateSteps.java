package com.robert.ddd.exchange;

import com.robert.ddd.InMemoryEURToRONExchangeRates;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsultEURToRONExchangeRateSteps {
    
    private EURToRONExchangeRates exchangeRates = new InMemoryEURToRONExchangeRates();
    private ConsultEURToRONExchangeRate sut = new ConsultEURToRONExchangeRate(exchangeRates);

    private BigDecimal currentExchangeRate;

    @Given("^I am a client of the bank$")
    public void noAction() {
    }

    @When("^I consult the EUR to RON exchange rate$")
    public void consultExchangeRate() {
        currentExchangeRate = sut.getEURToRONExchangeRate();
    }

    @Then("^the exchange rate is \"([^\"]*)\"$")
    public void the_exchange_rate_is(String expectedExchangeRate) {
        assertThat(currentExchangeRate).isEqualTo(new BigDecimal(expectedExchangeRate));
    }
}
