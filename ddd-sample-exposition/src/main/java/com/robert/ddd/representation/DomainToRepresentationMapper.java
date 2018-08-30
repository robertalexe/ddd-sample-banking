package com.robert.ddd.representation;

import com.robert.ddd.account.Account;
import com.robert.ddd.atm.ATMClient;
import com.robert.ddd.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DomainToRepresentationMapper {

    public static ATMClientRepresentation toRepresentation(ATMClient atmClient) {
        ATMClientRepresentation representation = new ATMClientRepresentation();
        representation.atmClientId = atmClient.getId().getUserId();
        representation.userName = atmClient.getIdentificationInformation().getClient().getUserName();
        representation.pin = String.valueOf(atmClient.getIdentificationInformation().getClient().getPin());
        representation.branchId = atmClient.getIdentificationInformation().getBankBranch().getBranchId();
        representation.branchName = atmClient.getIdentificationInformation().getBankBranch().getBranchName();
        representation.creationDate = atmClient.getGeneralDetails().getAccountCreationDate().toString();
        representation.lastLogin = atmClient.getGeneralDetails().getLastLogin().toString();
        representation.clientType = atmClient.getGeneralDetails().getClientType().toString();
        representation.accounts = parseAccounts(atmClient.getAccounts());
        return representation;
    }

    private static List<AccountRepresentation> parseAccounts(List<Account> accountList) {
        List<AccountRepresentation> accountRepresentations = new ArrayList<>();
        accountList.forEach( account -> {
            AccountRepresentation representation = new AccountRepresentation();
            representation.uniqueId = account.getId().getUniqueId();
            representation.iban = account.getIban();
            representation.currency = account.getAccountCurrency().toString();
            representation.balance = account.getAccountBalance().toString();

            representation.transactions = parseTransactions(account.getTransactions());
            accountRepresentations.add(representation);
        });
        return accountRepresentations;
    }

    public static AccountRepresentation parseAccount(Account account) {
        AccountRepresentation representation = new AccountRepresentation();
        representation.uniqueId = account.getId().getUniqueId();
        representation.iban = account.getIban();
        representation.balance = account.getAccountBalance().toString();
        representation.currency = account.getAccountCurrency().toString();
        representation.transactions = parseTransactions(account.getTransactions());
        return representation;
    }

    public static List<TransactionRepresentation> parseTransactions(List<Transaction> transactions) {
        List<TransactionRepresentation> transactionRepresentations = new ArrayList<>();
        transactions.forEach( transaction -> {
            TransactionRepresentation transact = new TransactionRepresentation();
            transact.transactionId = transaction.getId().getId();
            transact.amount = transaction.getAmount().toString();
            transact.transactionType = transaction.getTransactionType().toString();
            transact.transactionDate = transaction.getTransactionDate().toString();
            transactionRepresentations.add(transact);
        });
        return transactionRepresentations;
    }
}
