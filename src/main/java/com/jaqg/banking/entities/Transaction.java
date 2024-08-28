package com.jaqg.banking.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This helps auto-generate primary keys
    private long id;
    private LocalDateTime dateTime;
    private BigDecimal transVal;

    @Enumerated(EnumType.ORDINAL)
    private OperationType transType; // Transaction type can be withdraw, deposit, ect...peit
    @Transient
    private Account recipient; // to account
    @Transient
    private Account sender;


    public Transaction(LocalDateTime dateTime, BigDecimal transVal, OperationType transType, Account recipient) {
        this.dateTime = dateTime;
        this.transVal = transVal;
        this.transType = transType;
        this.recipient = recipient;
        this.sender = sender;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Transaction(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
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
