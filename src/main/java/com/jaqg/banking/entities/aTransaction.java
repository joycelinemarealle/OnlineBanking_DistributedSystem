package com.jaqg.banking.entities;

import com.jaqg.banking.enums.TransactionType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public abstract class aTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This helps auto-generate primary keys
    protected long id;

    @CreatedDate
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    protected LocalDateTime dateTime;

    @Column(precision = 16, scale = 2, nullable = false)
    protected BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    protected TransactionType type;

    public aTransaction(BigDecimal value, TransactionType type) {
        this.value = value;
        this.type = type;
    }

    public aTransaction() {
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal transVal) {
        this.value = transVal;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType transType) {
        this.type = transType;
    }

}
