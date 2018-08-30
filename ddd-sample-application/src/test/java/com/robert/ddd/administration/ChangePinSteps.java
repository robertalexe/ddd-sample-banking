package com.robert.ddd.administration;

import com.robert.ddd.InMemoryATMClients;
import com.robert.ddd.atm.*;
import com.robert.ddd.client.Client;
import com.robert.ddd.client.ClientType;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assertions;

import java.util.Collections;

import static java.time.LocalDateTime.now;

public class ChangePinSteps {

    private ATMClients clients = new InMemoryATMClients();

    private ChangeATMClientPin sut = new ChangeATMClientPin(clients);

    private ATMClientId currentId;

    @Given("^my user account id is \"([^\"]*)\" and my current pin is (\\d+)$")
    public void checkMyUserAccountId(String id, int oldPin) {
        currentId = new ATMClientId(id);
        ATMClient actualClient = new ATMClient(
                currentId,
                new IdentificationInformation(
                        new Client(
                            id, oldPin
                        ),
                        new BankBranch("A1234", "test")
                ),
                new GeneralDetails(
                        now(),
                        now(),
                        ClientType.GOLD
                ),
                Collections.emptyList()
        );
        clients.add(actualClient);
    }

    @Given("^my current pin is (\\d+)$")
    public void checkMyCurentPin(int oldPin) {
        Assertions.assertThat(clients.get(currentId).get().getIdentificationInformation().getClient().getPin()).isEqualTo(oldPin);
    }

    @When("^I perform the change PIN action and my new entered PIN is (\\d+)$")
    public void performChangePinAction(int newPin) {
        sut.changeATMClientPin(currentId, newPin);
    }

    @Then("^my account is updated and the new PIN is (\\d+)$")
    public void checkUpdatedPin(int newPin) {
        Assertions.assertThat(clients.get(currentId).get().getIdentificationInformation().getClient().getPin()).isEqualTo(newPin);

    }

}
