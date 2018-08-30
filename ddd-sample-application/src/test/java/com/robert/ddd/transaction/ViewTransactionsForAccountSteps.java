package com.robert.ddd.transaction;

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
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class ViewTransactionsForAccountSteps {

    private ATMClients clients = new InMemoryATMClients();
    private ViewTransactionsForAccount sut = new ViewTransactionsForAccount(clients);

    private ATMClientId clientId;
    private AccountId accountId;
    private List<Transaction> actualTransactions;

    @Given("^my user account id is \"([^\"]*)\" and my accountId is \"([^\"]*)\"$")
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
                                Collections.singletonList(
                                        new Transaction(
                                                new TransactionId(accountId, "1234"),
                                                new BigDecimal(100),
                                                TransactionType.DEPOSIT,
                                                LocalDateTime.of(2018, 04, 04, 00, 00, 00)
                                        )
                                )
                        )
                )
        );
        clients.add(client);
    }

    @When("^I consult my transactions list$")
    public void viewTransactions() {
        this.actualTransactions = sut.viewTransactions(accountId);
    }

    @Then("^I can see the following transaction:$")
    public void checkSingleTransaction(Map<String, String> expectedDetails) {
        Transaction actualTransaction = actualTransactions.stream().findFirst().get();
        assertThat(actualTransaction.getAmount()).isEqualTo(expectedDetails.get("amount"));
        assertThat(actualTransaction.getTransactionType().toString()).isEqualTo(expectedDetails.get("transactionType"));
        assertThat(actualTransaction.getTransactionDate().toString()).isEqualTo(expectedDetails.get("transactionDate"));
    }
}
