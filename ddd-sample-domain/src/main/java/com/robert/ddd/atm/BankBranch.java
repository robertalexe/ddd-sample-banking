package com.robert.ddd.atm;

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
public class BankBranch implements Validable {

    @BankBranch.BankBranchIdConstraint
    private String branchId;
    @BankBranch.BankBranchNameConstraint
    private String branchName;

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!this.getClass().isInstance(obj)) return false;
        BankBranch that = (BankBranch)obj;
        return that.getBranchName().equals(this.getBranchName()) &&
                that.getBranchId().equals(this.getBranchId());
    }

    public BankBranch(String branchId, String branchName) {
        this.branchId = branchId;
        this.branchName = branchName;
        validate(this);
    }

    private BankBranch() {
        this.branchId = null;
        this.branchName = null;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(new Object[] {branchId, branchName});
    }

    @Override
    public String toString() {
        return format(
                "%s{branchId=%s,branchName=%s}",
                getClass().getSimpleName(),
                branchId,
                branchName
        );
    }

    @Documented
    @Target({ElementType.FIELD})
    @Retention(RUNTIME)
    @javax.validation.Constraint(validatedBy = {})
    @NotNull
    @Size(min = 5, max = 5)
    private @interface BankBranchIdConstraint {
        String message() default "Business rules for Bank Branch Id are not respected or missing";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    @Documented
    @Target({ElementType.FIELD})
    @Retention(RUNTIME)
    @javax.validation.Constraint(validatedBy = {})
    @NotNull
    @Size(min = 0, max = 250)
    private @interface BankBranchNameConstraint {
        String message() default "Business rules for Bank Branch Name are not respected or missing";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
}
