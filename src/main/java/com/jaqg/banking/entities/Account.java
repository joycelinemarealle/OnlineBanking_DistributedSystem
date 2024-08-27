package com.jaqg.banking.entities;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long number;
    @Column(length = 200)
    private String name;
    private BigDecimal balance;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="recipient_id")
    private List<Transaction> transactions = new ArrayList<>();

    private int sortCode;

    public Account(long number, String name, BigDecimal balance, int sortCode) {
        this.number = number;
        this.name = name;
        this.balance = balance;
        this.sortCode = sortCode;
    }

    public Account (){}

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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public int getSortCode() {
        return sortCode;
    }

    public void setSortCode(int sortCode) {
        this.sortCode = sortCode;
    }

    @Override
    public String toString() {
        return "Account{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", transactions=" + transactions +
                ", sortCode=" + sortCode +
                '}';
    }
}
