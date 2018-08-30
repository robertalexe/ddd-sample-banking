package com.robert.ddd.clients;

import com.robert.ddd.InMemoryATMClients;
import com.robert.ddd.atm.*;
import com.robert.ddd.client.Client;
import com.robert.ddd.client.ClientType;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Collections;
import java.util.Set;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;

public class ViewAllClientsSteps {

    private ATMClients clients = new InMemoryATMClients();
    private ViewAllClients sut = new ViewAllClients(clients);

    private Set<Client> actualListOfClients;

    @Given("^I am an administrator of the application$")
    public void iAmAdministrator() {
    }

    @Given("^the application has (\\d+) registered clients$")
    public void setNumberOfRegisteredClients(int numberOfRegisteredClients) {
        generateClients(numberOfRegisteredClients, clients);
    }

    @When("^I request the list of the active clients$")
    public void requestListOfClients() {
        actualListOfClients = sut.viewAllClients();
    }

    @Then("^I receive a list containing (\\d+) clients$")
    public void checkNumberOfClientsRetrieved(int expectedNumberOfClients) {
        assertThat(actualListOfClients.size()).isEqualTo(expectedNumberOfClients);
    }

    private static void generateClients(int numberOfclients, ATMClients repo) {
        for(int i=1; i<=numberOfclients; i++) {
            ATMClient client = new ATMClient(
                    new ATMClientId(String.valueOf(i) + "23456"),
                    new IdentificationInformation(
                            new Client(
                                    String.valueOf(i) + "23456", 1234
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
            repo.add(client);
        }
    }
}
