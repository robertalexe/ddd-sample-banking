package com.robert.ddd;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;

public interface Validable {

    default <T> void validate() {
        validate(this);
    }
    default <T> void validate(T object) {
        Set<ConstraintViolation<T>> actualViolations = Validation.buildDefaultValidatorFactory().getValidator().validate(object, new Class[0]);
        if(!actualViolations.isEmpty())
            throw new IllegalArgumentException(actualViolations.toString());
    }
}
