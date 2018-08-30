package com.robert.ddd.atm;

import com.robert.ddd.account.Account;
import com.robert.ddd.account.AccountCurrency;
import com.robert.ddd.account.AccountId;
import com.robert.ddd.client.Client;
import com.robert.ddd.client.ClientType;
import com.robert.ddd.transaction.Transaction;
import com.robert.ddd.transaction.TransactionId;
import com.robert.ddd.transaction.TransactionType;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ATMClientCreationTest {

    private String A_VALID_CLIENT_ID = "999999";
    private Client A_VALID_CLIENT = new Client("user123", 1234);
    private BankBranch A_VALID_BRANCH = new BankBranch("12345", "Bucharest Branch");
    private LocalDateTime A_VALID_DATE = LocalDateTime.of(2018, 1, 1, 0, 0, 0);
    private Account A_VALID_ACCOUNT_WITH_TRANSACTION = generateAccount();

    @Test
    public void atm_client_creation_test() {
        ATMClient atmClient = new ATMClient(
                new ATMClientId(A_VALID_CLIENT_ID),
                new IdentificationInformation(
                        A_VALID_CLIENT,
                        A_VALID_BRANCH
                ),
                new GeneralDetails(
                       A_VALID_DATE,
                       A_VALID_DATE,
                       ClientType.GOLD
                ),
                singletonList(A_VALID_ACCOUNT_WITH_TRANSACTION)
        );
        assertThat(atmClient).isNotNull();
    }

    private Account generateAccount() {
        AccountId accountId = new AccountId(
                new ATMClientId(A_VALID_CLIENT_ID),
                "A1234567"
        );
        Transaction transaction = new Transaction(
                new TransactionId(
                        accountId,
                        "id-1234"
                ),
                new BigDecimal(100),
                TransactionType.DEPOSIT,
                A_VALID_DATE
        );
        return new Account(
                accountId,
                "RO10293SVV",
                AccountCurrency.EUR,
                new BigDecimal(100),
                singletonList(transaction)
        );
    }
}
