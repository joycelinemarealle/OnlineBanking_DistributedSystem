package com.jaqg.banking.DTO;

import com.jaqg.banking.entities.Customer;

public class GetAccountResponseDTO {
    private Long number;
    private Long sortCode;
    private Double openingBalance;
    private Integer[] transactions;
    private Double balance;
    private Customer customer;

    public GetAccountResponseDTO(Long number, Long sortCode, Double openingBalance, Integer[] transactions, Double balance, Customer customer) {
        this.number = number;
        this.sortCode = sortCode;
        this.openingBalance = openingBalance;
        this.transactions = transactions;
        this.balance = balance;
        this.customer = customer;
    }

    public GetAccountResponseDTO() {}

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getSortCode() {
        return sortCode;
    }

    public void setSortCode(Long sortCode) {
        this.sortCode = sortCode;
    }

    public Double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(Double openingBalance) {
        this.openingBalance = openingBalance;
    }

    public Integer[] getTransactions() {
        return transactions;
    }

    public void setTransactions(Integer[] transactions) {
        this.transactions = transactions;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
