package com.robert.ddd.deposit;

import com.robert.ddd.InMemoryATMClients;
import com.robert.ddd.account.Account;
import com.robert.ddd.account.AccountCurrency;
import com.robert.ddd.account.AccountId;
import com.robert.ddd.atm.*;
import com.robert.ddd.client.Client;
import com.robert.ddd.client.ClientType;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assertions;

import java.math.BigDecimal;
import java.util.ArrayList;

import static java.time.LocalDateTime.now;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class DepositInAccountSteps {

    private ATMClients clients = new InMemoryATMClients();
    private DepositInAccount sut = new DepositInAccount(clients);

    private ATMClientId clientId;
    private AccountId accountId;

    private Account account;

    @Given("^my user account id is \"([^\"]*)\" and my base account number is \"([^\"]*)\" and the account balance is \"([^\"]*)\"$")
    public void setAccountDetails(String userId, String accountNumber, String balance) {
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
                                new BigDecimal(balance),
                                new ArrayList<>()
                        )
                )
        );
        clients.add(client);
    }

    @When("^I deposit (\\d+) units$")
    public void depositMoney(int amount) {
        sut.depositMoneyInAccount(accountId, new BigDecimal(amount));
    }

    @Then("^my account is updated and the balance is \"([^\"]*)\"$")
    public void checkAccountBalance(String amount) {
        account = clients.get(clientId).get().getAccounts().stream().findFirst().get();
        assertThat(account.getAccountBalance()).isEqualTo(new BigDecimal(amount));
    }
}
