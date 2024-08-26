package com.jaqg.banking.entities;

import jakarta.persistence.*;


import javax.annotation.processing.Generated;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Account {
    @Id
    private long number;
    private String name;
    private BigDecimal openingBalance;
    private BigDecimal balance;
    private List<Transaction> transactions;
    private int sortCode;

    public Account(long number, String name, BigDecimal openingBalance, BigDecimal balance, Customer customer, List<Transaction> transactions, int sortCode) {
        this.number = number;
        this.name = name;
        this.openingBalance = openingBalance;
        this.balance = balance;
        this.customer = customer;
        this.transactions = transactions;
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
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public int getSortCode() {
        return sortCode;
    }

    public void setSortCode(int sortCode) {
        this.sortCode = sortCode;
    }

    public boolean isClosed(){
        return this.isClosed;
    }

    public void setClosed(boolean closed){
        isClosed = closed;

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
