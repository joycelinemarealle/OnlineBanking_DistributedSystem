package com.jaqg.banking.entities;

import com.jaqg.banking.enums.TransactionType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("WITHDRAWAL")
public class Withdrawal extends aTransaction implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long fromAccount; // to account

    private Long fromAccountSourceCode;

    public Withdrawal(BigDecimal value, Long fromAccount) {
        super(value, TransactionType.WITHDRAWAL);
        setFromAccount(fromAccount);
    }

    public Withdrawal() {
    }

    public Long getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Long recipient) {
        if (recipient != null) {
//            recipient.addCreditTransaction(this);
        }
        this.fromAccount = recipient;
    }
}
