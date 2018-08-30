package com.robert.ddd.atm;

import com.robert.ddd.DDD;
import com.robert.ddd.Validable;
import com.robert.ddd.account.Account;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@DDD.AggregateRoot
@DDD.Entity
public class ATMClient implements Validable {

    @NotNull
    private ATMClientId id;
    @NotNull
    private IdentificationInformation identificationInformation;
    @NotNull
    private GeneralDetails generalDetails;
    private List<Account> accounts;

    public ATMClient(ATMClientId id, IdentificationInformation identificationInformation, GeneralDetails generalDetails, List<Account> accounts) {
        this.id = id;
        this.identificationInformation = identificationInformation;
        this.generalDetails = generalDetails;
        this.accounts = accounts;
        validate(this);
    }

    private ATMClient() {
        this.id = null;
        this.identificationInformation = null;
        this.generalDetails = null;
        this.accounts = null;
    }

    public ATMClientId getId() {
        return id;
    }

    public IdentificationInformation getIdentificationInformation() {
        return identificationInformation;
    }

    public GeneralDetails getGeneralDetails() {
        return generalDetails;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void updateIdentificationInformation(IdentificationInformation identificationInformation) {
        this.identificationInformation = identificationInformation;
        validate();
    }

    public void updateGeneralDetails(GeneralDetails generalDetails) {
        this.generalDetails = generalDetails;
    }

    public Optional<Account> findAccountByUniqueId(String uniqueId) {
        return accounts.stream().filter(account -> account.getId().getUniqueId().equals(uniqueId)).findFirst();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!this.getClass().isInstance(obj)) return false;
        ATMClient that = (ATMClient)obj;
        return that.getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    @Override
    public String toString() {
        return format(
                "%s{id=%s,identificationInformation=%s,generalDetails=%s,accounts=%s}",
                getClass().getSimpleName(),
                id,
                identificationInformation,
                generalDetails,
                accounts
        );
    }
}
