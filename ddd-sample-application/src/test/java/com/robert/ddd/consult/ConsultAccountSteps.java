package com.robert.ddd.consult;

import com.robert.ddd.account.Account;
import com.robert.ddd.account.AccountCurrency;
import com.robert.ddd.account.AccountId;
import com.robert.ddd.InMemoryATMClients;
import com.robert.ddd.atm.*;
import com.robert.ddd.client.Client;
import com.robert.ddd.client.ClientType;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.math.BigDecimal;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class ConsultAccountSteps {

    private ATMClients clients = new InMemoryATMClients();
    private ConsultAccount sut = new ConsultAccount(clients);

    private ATMClientId clientId;
    private AccountId accountId;
    private Account actualAccount;

    @Given("^my user account id is \"([^\"]*)\" and my account number is \"([^\"]*)\"$")
    public void setMyAccountDetails(String userId, String accountNumber) {
        clientId = new ATMClientId(userId);
        accountId = new AccountId(clientId, accountNumber);
        ATMClient client = new ATMClient(
                clientId,
                new IdentificationInformation(
                        new Client(
                                userId, 1234
                        ),
                        new BankBranch("A1234", "test")
                ),
                new GeneralDetails(
                        now(),
                        now(),
                        ClientType.GOLD
                ),
                singletonList(
                        new Account(
                                accountId,
                                "RO12345",
                                AccountCurrency.EUR,
                                new BigDecimal(100),
                                emptyList()
                        )
                )
        );
        clients.add(client);
    }

    @When("^I perform the consult action on my account$")
    public void consultAccount() {
        actualAccount = sut.viewAccount(accountId);
    }

    @Then("^the following details are retrieved:$")
    public void checkReturnedAccountDetails(Map<String, String> expectedDetails) {
        assertThat(actualAccount.getId().getUniqueId()).isEqualTo(expectedDetails.get("accountId"));
        assertThat(actualAccount.getIban()).isEqualTo(expectedDetails.get("iban"));
        assertThat(actualAccount.getAccountCurrency().toString()).isEqualTo(expectedDetails.get("accountCurrency"));
        assertThat(actualAccount.getAccountBalance()).isEqualTo(new BigDecimal(expectedDetails.get("accountBalance")));
    }
}
