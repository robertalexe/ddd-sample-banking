package com.robert.ddd.account;

import com.robert.ddd.DDD;
import com.robert.ddd.Validable;
import com.robert.ddd.transaction.Transaction;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

import static java.lang.String.format;

@DDD.Entity
public class Account implements Validable {

    private AccountId id;
    @NotNull
    private String iban;
    @NotNull
    private AccountCurrency accountCurrency;
    @NotNull
    private BigDecimal accountBalance;
    private List<Transaction> transactions;

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!this.getClass().isInstance(obj)) return false;
        Account that = (Account)obj;
        return that.getId().equals(this.getId());
    }

    public Account(AccountId id, String iban, AccountCurrency accountCurrency, BigDecimal accountBalance, List<Transaction> transactions) {
        this.id = id;
        this.iban = iban;
        this.accountCurrency = accountCurrency;
        this.accountBalance = accountBalance;
        this.transactions = transactions;
        validate(this);
    }

    private Account() {
        this.id = null;
        this.iban = null;
        this.accountCurrency = null;
        this.accountBalance = null;
        this.transactions = null;
    }

    public AccountId getId() {
        return id;
    }

    public String getIban() {
        return iban;
    }

    public AccountCurrency getAccountCurrency() {
        return accountCurrency;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addAmount(BigDecimal amount) {
        this.accountBalance = accountBalance.add(amount);
    }

    public void withdrawAmount(BigDecimal amount) { this.accountBalance = accountBalance.subtract(amount); }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    @Override
    public String toString() {
        return format(
                "%s{id=%s,iban=%s,accountCurrency=%s,accountBalance=%s,transactions=%s}",
                getClass().getSimpleName(),
                id,
                iban,
                accountCurrency,
                accountBalance,
                transactions);
    }
}
