package com.robert.ddd.transaction;

import com.robert.ddd.DDD;
import com.robert.ddd.Validable;
import com.robert.ddd.account.AccountId;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static java.lang.String.format;

@DDD.ValueObjectId
public class TransactionId implements Serializable, Validable {

    @NotNull
    private AccountId accountId;
    @NotNull
    private String id;

    public TransactionId(AccountId accountId, String id) {
        this.accountId = accountId;
        this.id = id;
        validate(this);
    }

    private TransactionId() {
        this.accountId = null;
        this.id = null;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return format(
                "%s{accountId=%s,id=%s}",
                getClass().getSimpleName(),
                accountId,
                id
        );
    }
}
