package com.robert.ddd.account;

import com.robert.ddd.DDD;
import com.robert.ddd.Validable;
import com.robert.ddd.atm.ATMClientId;

import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.lang.annotation.*;

import static java.lang.String.format;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@DDD.ValueObjectId
public class AccountId implements Serializable, Validable {

    private ATMClientId atmId;
    @AccountId.AccountUniqueIdConstraint
    private String uniqueId;

    public AccountId(ATMClientId atmId, String uniqueId) {
        this.atmId = atmId;
        this.uniqueId = uniqueId;
        validate(this);
    }

    private AccountId() {
        this.atmId = null;
        this.uniqueId = null;
    }

    public ATMClientId getAtmId() {
        return atmId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    @Override
    public String toString() {
        return format(
                "%s{atmId=%s,uniqueId=%s}",
                getClass().getSimpleName(),
                atmId,
                uniqueId
        );
    }

    @Documented
    @Target({ElementType.FIELD})
    @Retention(RUNTIME)
    @javax.validation.Constraint(validatedBy = {})
    @NotNull
    @Size(min = 8, max = 8)
    private @interface AccountUniqueIdConstraint {
        String message() default "Business rules for Account Unique Id are not respected or missing";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
}
