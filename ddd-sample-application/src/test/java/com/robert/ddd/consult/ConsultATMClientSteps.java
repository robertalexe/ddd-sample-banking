package com.robert.ddd.consult;

import com.robert.ddd.InMemoryATMClients;
import com.robert.ddd.atm.*;
import com.robert.ddd.client.Client;
import com.robert.ddd.client.ClientType;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.time.LocalDateTime;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

public class ConsultATMClientSteps {

    private ATMClients clients = new InMemoryATMClients();
    private ConsultAtmClient sut = new ConsultAtmClient(clients);

    private ATMClientId clientId;
    private ATMClient actualClient;

    @Given("^my user account id is \"([^\"]*)\"$")
    public void setMyProfileDetails(String userId) {
        clientId = new ATMClientId(userId);
        ATMClient client = new ATMClient(
                clientId,
                new IdentificationInformation(
                        new Client(
                                userId, 1234
                        ),
                        new BankBranch("A1234", "Bucharest Central Branch")
                ),
                new GeneralDetails(
                        LocalDateTime.of(2018, 04, 04, 00, 00, 00),
                        now(),
                        ClientType.GOLD
                ),
                emptyList()
        );
        clients.add(client);
    }

    @When("^I perform the consult action on my profile$")
    public void consultProfileDetails() {
        actualClient = sut.viewClient(clientId);
    }

    @Then("^the following details are retrieved concerning my profile:$")
    public void checkReturnedProfileDetails(Map<String, String> expectedDetails) {
        assertThat(actualClient.getId().getUserId()).isEqualTo(expectedDetails.get("id"));
        assertThat(actualClient.getIdentificationInformation().getBankBranch().getBranchName()).isEqualTo(expectedDetails.get("bankBranchName"));
        assertThat(actualClient.getGeneralDetails().getAccountCreationDate().toString()).isEqualTo(expectedDetails.get("accountCreation"));
        assertThat(actualClient.getGeneralDetails().getClientType().toString()).isEqualTo(expectedDetails.get("clientType"));
    }
}
