package com.robert.ddd.atm;

import com.robert.ddd.DDD;
import com.robert.ddd.Validable;
import com.robert.ddd.client.ClientType;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

import static java.lang.String.format;

@DDD.ValueObject
public class GeneralDetails implements Validable {

    @NotNull
    private LocalDateTime accountCreationDate;
    @NotNull
    private LocalDateTime lastLogin;
    @NotNull
    private ClientType clientType;

    public GeneralDetails(LocalDateTime accountCreationDate, LocalDateTime lastLogin, ClientType clientType) {
        this.accountCreationDate = accountCreationDate;
        this.lastLogin = lastLogin;
        this.clientType = clientType;
        validate(this);
    }

    private GeneralDetails() {
        this.accountCreationDate = null;
        this.lastLogin = null;
        this.clientType = null;
    }

    public LocalDateTime getAccountCreationDate() {
        return accountCreationDate;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public ClientType getClientType() {
        return clientType;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!this.getClass().isInstance(obj)) return false;
        GeneralDetails that = (GeneralDetails) obj;
        return that.getAccountCreationDate().equals(this.getAccountCreationDate()) &&
                that.getLastLogin().equals(this.getLastLogin()) &&
                that.getClientType().equals(this.getClientType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(new Object[] {clientType, lastLogin, accountCreationDate});
    }

    @Override
    public String toString() {
        return format(
                "%s{accountCreationDate=%s,lastLogin=%s,clientType=%s}",
                getClass().getSimpleName(),
                accountCreationDate,
                lastLogin,
                clientType
        );
    }
}
