package com.jaqg.banking.entities;

import com.jaqg.banking.Constants;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@SequenceGenerator(
        name = Constants.ACCOUNT_NUMBER_GENERATOR,
        sequenceName = "account_number_seq",
        initialValue = 100000, allocationSize = 1)
public class AccountPK implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @GeneratedValue(generator = Constants.ACCOUNT_NUMBER_GENERATOR)
    protected long number;

    @NotNull
    protected int sortCode;

    public AccountPK() {

    }

    public AccountPK(int sortCode) {
        this.sortCode = sortCode;
    }

    public AccountPK(long number, int sortCode) {
        this.number = number;
        this.sortCode = sortCode;
    }

    public long number() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int sortCode() {
        return sortCode;
    }

    public void setSortCode(int sortCode) {
        this.sortCode = sortCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountPK accountPK)) return false;
        return number == accountPK.number && sortCode == accountPK.sortCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, sortCode);
    }
}
