package com.robert.ddd.atm;

import com.robert.ddd.DDD;
import com.robert.ddd.Validable;
import com.robert.ddd.client.Client;

import javax.validation.constraints.NotNull;
import java.util.Objects;

import static java.lang.String.format;

@DDD.ValueObject
public class IdentificationInformation implements Validable {

    @NotNull
    private Client client;
    @NotNull
    private BankBranch bankBranch;

    public IdentificationInformation(Client client, BankBranch bankBranch) {
        this.client = client;
        this.bankBranch = bankBranch;
        validate(this);
    }

    private IdentificationInformation() {
        this.client = null;
        this.bankBranch = null;
    }

    public Client getClient() {
        return client;
    }

    public BankBranch getBankBranch() {
        return bankBranch;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!this.getClass().isInstance(obj)) return false;
        IdentificationInformation that = (IdentificationInformation) obj;
        return that.getClient().equals(this.getClient()) &&
                that.getBankBranch().equals(this.getBankBranch());
    }

    @Override
    public int hashCode() {
        return Objects.hash(new Object[] {client, bankBranch});
    }

    @Override
    public String toString() {
        return format(
                "%s{client=%s,bankBranch=%s}",
                getClass().getSimpleName(),
                client,
                bankBranch
        );
    }
}
