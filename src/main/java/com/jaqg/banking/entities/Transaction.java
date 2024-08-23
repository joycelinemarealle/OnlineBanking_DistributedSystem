package com.jaqg.banking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate dateTime;
    private BigDecimal transVal;
    private String transType; // Transaction type can be withdraw, deposit, ect...
    private String recipient; // to account
    // from account (TBD because it's implied that the account handling the transaction is the account where the transaction is coming from

    public Transaction(Long id, LocalDate dateTime, BigDecimal transVal, String transType, String recipient) {
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

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getRecipient() {
        return recipient;
    }

    public void String(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", transVal=" + transVal +
                ", transType=" + transType +
                ", recipient=" + recipient +
                '}';
    }
}
