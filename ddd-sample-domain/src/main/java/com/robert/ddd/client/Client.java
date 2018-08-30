package com.robert.ddd.client;

import com.robert.ddd.DDD;
import com.robert.ddd.Validable;

import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Objects;

import static java.lang.String.format;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@DDD.ValueObject
public class Client implements Validable {

    @Client.UsernameConstraint
    private String userName;
    @Client.PinConstraint
    private int pin;

    public Client(String userName, int pin) {
        this.userName = userName;
        this.pin = pin;
        validate(this);
    }

    private Client() {
        this.userName = null;
    }

    public String getUserName() {
        return userName;
    }

    public int getPin() {
        return pin;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!this.getClass().isInstance(obj)) return false;
        Client that = (Client) obj;
        return that.getPin() == this.getPin() &&
                that.getUserName().equals(this.getUserName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(new Object[] {userName, pin});
    }

    @Override
    public String toString() {
        return format(
                "%s{username=%s,pin=%s}",
                getClass().getSimpleName(),
                userName,
                pin
        );
    }

    @Documented
    @Target({ElementType.FIELD})
    @Retention(RUNTIME)
    @javax.validation.Constraint(validatedBy = {})
    @NotNull
    @Size(min = 5, max = 30)
    private @interface UsernameConstraint {
        String message() default "Business rules for User Name are not respected or missing";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    @Documented
    @Target({ElementType.FIELD})
    @Retention(RUNTIME)
    @javax.validation.Constraint(validatedBy = {})
    @NotNull
    public @interface PinConstraint {
        String message() default "Business rules for PIN are not respected or missing";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
}
