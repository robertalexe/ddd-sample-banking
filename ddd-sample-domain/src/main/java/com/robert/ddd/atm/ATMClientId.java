package com.robert.ddd.atm;

import com.robert.ddd.DDD;
import com.robert.ddd.Validable;

import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.String.format;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@DDD.ValueObjectId
public class ATMClientId implements Serializable, Validable {

    @ATMClientId.UserIdConstraint
    private String userId;

    public ATMClientId(String userId) {
        this.userId = userId;
        validate(this);
    }

    private ATMClientId() {
        this.userId = null;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return format(
                "%s{userId=%s}",
                getClass().getSimpleName(),
                userId
        );
    }

    @Documented
    @Target({ElementType.FIELD})
    @Retention(RUNTIME)
    @javax.validation.Constraint(validatedBy = {})
    @NotNull
    @Size(min = 6, max = 6)
    private @interface UserIdConstraint {
        String message() default "Business rules for Client Id are not respected or missing";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
}
