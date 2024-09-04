package com.jaqg.banking.entities;

import com.jaqg.banking.enums.TransactionType;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("DEPOSIT")
public class Deposit extends aTransaction implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long toAccount; // to account

    private Long toAccountSourceCode;

    public Deposit(BigDecimal value, Long toAccount) {
        super(value, TransactionType.DEPOSIT);
        setToAccount(toAccount);
    }

    public Deposit() {
    }

    public Long getToAccount() {
        return toAccount;
    }

    public void setToAccount(Long recipient) {
        if (recipient != null) {
//            recipient.addCreditTransaction(this);
        }
        this.toAccount = recipient;
    }
}
