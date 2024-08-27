package com.jaqg.banking.dto;

import java.time.LocalDate;

public class TransactionResponse {
    private LocalDate time;
    private String type;
    private int fromAccount;
    private int fromAccountSortCode;
    private int toAccount;
    private int toAccountSortCode;
    private double amount;

    
    public String getType() {
        return type;
    }

    
    public void setType(String type) {
        this.type = type;
    }

    public int getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(int fromAccount) {
        this.fromAccount = fromAccount;
    }

    public int getFromAccountSortCode() {
        return fromAccountSortCode;
    }

    public void setFromAccountSortCode(int fromAccountSortCode) {
        this.fromAccountSortCode = fromAccountSortCode;
    }

    public int getToAccount() {
        return toAccount;
    }

    public void setToAccount(int toAccount) {
        this.toAccount = toAccount;
    }

    public int getToAccountSortCode() {
        return toAccountSortCode;
    }

    public void setToAccountSortCode(int toAccountSortCode) {
        this.toAccountSortCode = toAccountSortCode;
    }

    
    public double getAmount() {
        return amount;
    }

    
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }
}
