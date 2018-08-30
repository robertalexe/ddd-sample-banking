package com.robert.ddd.transaction;

import com.robert.ddd.DDD;
import com.robert.ddd.Validable;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.lang.String.format;

@DDD.Entity
public class Transaction implements Validable {

    @NotNull
    private TransactionId id;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private TransactionType transactionType;
    @NotNull
    private LocalDateTime transactionDate;

    public Transaction(TransactionId id, BigDecimal amount, TransactionType transactionType, LocalDateTime transactionDate) {
        this.id = id;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        validate(this);
    }

    private Transaction() {
        this.id = null;
        this.amount = null;
        this.transactionType = null;
        this.transactionDate = null;
    }

    public TransactionId getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!this.getClass().isInstance(obj)) return false;
        Transaction that = (Transaction)obj;
        return that.getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    @Override
    public String toString() {
        return format(
                "%s{id=%s,amount=%s,transactionType=%s,transactionDate=%s}",
                getClass().getSimpleName(),
                id,
                amount,
                transactionType,
                transactionDate
        );
    }
}
