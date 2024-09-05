//package com.jaqg.banking.entities;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//
//import java.io.Serial;
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Stream;
//
//
//@Entity
//public abstract class AccountDelete implements Serializable {
//
//    @Serial
//    private static final long serialVersionUID = 1L;
//
//    @EmbeddedId
//    protected AccountPK id;
//
//    @Column(length = 50, nullable = false)
//    @NotBlank(message = "Name is mandatory")
//    @NotNull
//    private String name;
//
//    @Column(precision = 16, scale = 2, nullable = false)
//    @NotNull
//    private BigDecimal openingBalance;
//
//    @Column(precision = 16, scale = 2, nullable = false)
//    @NotNull
//    private BigDecimal balance;
//
//    @NotNull
//    private boolean isClosed = false;
//
//    @ManyToOne
//    private Customer customer;
//
//    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @OrderBy(value = "dateTime desc")
//    private final List<Transaction> debitTransactions = new ArrayList<>();
//
//    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @OrderBy(value = "dateTime desc")
//    private final List<Transaction> creditTransactions = new ArrayList<>();
//
//    public AccountDelete(String name, BigDecimal openingBalance, Customer customer, Integer sortCode) {
//        this.name = name;
//        this.openingBalance = openingBalance;
//        this.balance = openingBalance;
//        this.customer = customer;
//        this.id = new AccountPK(sortCode);
//    }
//
//    public AccountDelete(Long id, String name, BigDecimal openingBalance, Customer customer, Integer sortCode) {
//        this.name = name;
//        this.openingBalance = openingBalance;
//        this.balance = openingBalance;
//        this.customer = customer;
//        this.id = new AccountPK(id, sortCode);
//    }
//
//    public AccountDelete() {
//        this.id = new AccountPK();
//    }
//
//    public long getNumber() {
//        return id.number();
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public BigDecimal getOpeningBalance() {
//        return openingBalance;
//    }
//
//    public void setOpeningBalance(BigDecimal openingBalance) {
//        this.openingBalance = openingBalance;
//    }
//
//    public BigDecimal getBalance() {
//        return balance;
//    }
//
//    public void setBalance(BigDecimal balance) {
//        this.balance = balance;
//    }
//
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//        customer.addAccount(this);
//    }
//
//    public List<Transaction> getTransactions() {
//        return Stream.concat(debitTransactions.stream(), creditTransactions.stream()).sorted().toList();
//    }
//
//    public void addDebitTransaction(Transaction transaction) {
//        validateTransaction(transaction);
//        debitTransactions.add(transaction);
//    }
//
//    public void addCreditTransaction(Transaction transaction) {
//        validateTransaction(transaction);
//        creditTransactions.add(transaction);
//    }
//
//    public Integer getSortCode() {
//        return id.sortCode();
//    }
//
//    public void setSortCode(int sortCode) {
//        this.id.setSortCode(sortCode);
//    }
//
//    public boolean isClosed() {
//        return this.isClosed;
//    }
//
//    public void setClosed(boolean closed) {
//        isClosed = closed;
//
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof AccountDelete account)) return false;
//        return Objects.equals(id, account.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(id);
//    }
//
//    @Override
//    public String toString() {
//        return "Account{" +
//                "number=" + id.number() +
//                ", sortCode=" + id.sortCode() +
//                ", name='" + name + '\'' +
//                ", balance=" + balance +
//                '}';
//    }
//
//    private void validateTransaction(Transaction transaction) {
//        if (transaction == null) {
//            throw new NullPointerException("Can't add null transaction");
//        }
//    }
//}
