package com.jaqg.banking.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY) // This helps auto-generate primry keys
    private long id;
    private LocalDate dateTime;
    private BigDecimal transVal;
    @Transient
    private OperationType transType; // Transaction type can be withdraw, deposit, ect...
    private Account recipient; // to account
    // from account (TBD because it's implied that the account handling the transaction is the account where the transaction is coming from


    public Transaction(long id, LocalDate dateTime, BigDecimal transVal, OperationType transType, Account recipient) {
        this.id = id;
        this.dateTime = dateTime;
        this.transVal = transVal;
        this.transType = transType;
        this.recipient = recipient;
    }
    public Transaction(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getTransVal() {
        return transVal;
    }

    public void setTransVal(BigDecimal transVal) {
        this.transVal = transVal;
    }

    public OperationType getTransType() {
        return transType;
    }

    public void setTransType(OperationType transType) {
        this.transType = transType;
    }

    public Account getRecipient() {
        return recipient;
    }

    public void setRecipient(Account recipient) {
        this.recipient = recipient;
    }
}
