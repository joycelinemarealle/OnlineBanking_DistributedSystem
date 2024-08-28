package com.jaqg.banking.entities;

import com.jaqg.banking.enums.OperationType;
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

    @Column(precision=16, scale=2)
    private BigDecimal transVal;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private OperationType transType; // Transaction type can be withdraw, deposit, ect...peit

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Account recipient; // to account

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Account sender;


    public Transaction(LocalDateTime dateTime, BigDecimal transVal, OperationType transType, Account recipient, Account sender) {
        this.dateTime = dateTime;
        this.transVal = transVal;
        this.transType = transType;
        this.recipient = recipient;
        this.sender = sender;
    }

    public Transaction() {
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

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
