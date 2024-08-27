package com.jaqg.banking.entities;

import jakarta.persistence.*;


import javax.annotation.processing.Generated;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long number;
    private String name;
    private BigDecimal openingBalance;
    private BigDecimal balance;
    private boolean isClosed = false;

    @ManyToOne
    @JoinColumn(name= "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "account", cascade=CascadeType.ALL)
    //account does not own the relationship but transcation does
    private List<Transaction> transactions = new ArrayList<>();
    private Integer sortCode;

    public Account(long number, String name, BigDecimal openingBalance, BigDecimal balance, Customer customer,  Integer sortCode) {
        this.number = number;
        this.name = name;
        this.openingBalance = openingBalance;
        this.balance = balance;
        this.customer = customer;
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

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public Integer getSortCode() {
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
                ", transactions=" + transactions +
                ", sortCode=" + sortCode +
                '}';
    }
}
