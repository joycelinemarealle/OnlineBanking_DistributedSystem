package com.jaqg.banking.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Entity
public class Account implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long number;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(precision=16, scale=2, nullable = false)
    private BigDecimal openingBalance;

    @Column(precision=16, scale=2, nullable = false)
    private BigDecimal balance;

    private boolean isClosed = false;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private Integer sortCode;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    //account does not own the relationship but transcation does
    private final List<Transaction> depositTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    //account does not own the relationship but transcation does
    private final List<Transaction> creditTransactions = new ArrayList<>();

    public Account(long number, String name, BigDecimal openingBalance, BigDecimal balance, Customer customer, Integer sortCode) {
        this.number = number;
        this.name = name;
        this.openingBalance = openingBalance;
        this.balance = balance;
        this.customer = customer;
        this.sortCode = sortCode;
    }


    public Account() {
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(BigDecimal openingBalance) {
        this.openingBalance = openingBalance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Transaction> getTransactions() {
        return Stream.concat(depositTransactions.stream(), creditTransactions.stream()).toList();
    }

    public void addDebitTransaction(Transaction transaction) {
        depositTransactions.add(transaction);
    }

    public void addCreditTransaction(Transaction transaction) {
        creditTransactions.add(transaction);
    }

    public Integer getSortCode() {
        return sortCode;
    }

    public void setSortCode(int sortCode) {
        this.sortCode = sortCode;
    }

    public boolean isClosed() {
        return this.isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return number == account.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }

    @Override
    public String toString() {
        return "Account{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", sortCode=" + sortCode +
                '}';
    }
}
