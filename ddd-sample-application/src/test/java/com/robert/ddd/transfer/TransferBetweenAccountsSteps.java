package com.robert.ddd.transfer;

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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;

public class TransferBetweenAccountsSteps {

    private ATMClients clients = new InMemoryATMClients();
    private TransferBetweenAccounts sut = new TransferBetweenAccounts(clients);

    private ATMClientId clientId;
    private AccountId baseAccountId;
    private AccountId secondAccountId;

    private Account baseAccount;
    private Account secondAccount;


    @Given("^my user account id is \"([^\"]*)\" and my base account number is \"([^\"]*)\" and the current account balance is \"([^\"]*)\" and the second account is \"([^\"]*)\" and balance is \"([^\"]*)\"$")
    public void setAccountDetails(String userId, String baseAccount, String baseBalance, String secondAccount, String secondBalance) {
        clientId = new ATMClientId(userId);
        baseAccountId = new AccountId(clientId, baseAccount);
        secondAccountId = new AccountId(clientId, secondAccount);
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
                Arrays.asList(
                        new Account(
                                baseAccountId,
                                "RO12345",
                                AccountCurrency.EUR,
                                new BigDecimal(baseBalance),
                                new ArrayList<>()
                        ),
                        new Account(
                                secondAccountId,
                                "RO12345",
                                AccountCurrency.EUR,
                                new BigDecimal(secondBalance),
                                new ArrayList<>()
                        )
                )
        );
        clients.add(client);
    }

    @When("^I transfer (\\d+) units from base account to second account$")
    public void performTransferBetweenAccounts(int amountToTransfer) {
        sut.transferMoneyBetweenAccounts(baseAccountId, secondAccountId, new BigDecimal(amountToTransfer));
        ATMClient singleClient = clients.findAll().stream().findFirst().get();
        baseAccount = singleClient.getAccounts().stream().filter( acc -> acc.getId().equals(baseAccountId)).findFirst().get();
        secondAccount = singleClient.getAccounts().stream().filter( acc -> acc.getId().equals(secondAccountId)).findFirst().get();
    }

    @Then("^the base account's balance is \"([^\"]*)\" and the second account's balance is \"([^\"]*)\"$")
    public void checkAccountsBalance(String expectedFirstAccountAmount, String expectedSecondAccountAmount) {
        assertThat(baseAccount.getAccountBalance()).isEqualTo(expectedFirstAccountAmount);
        assertThat(secondAccount.getAccountBalance()).isEqualTo(expectedSecondAccountAmount);
    }
    
}
