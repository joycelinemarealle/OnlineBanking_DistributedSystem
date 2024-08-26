package com.jaqg.banking.dto;

public class TransactionRequest {
    private String type;
    private int fromAcount;
    private int fromAcountSortCode;
    private int toAcount;
    private int toAcountSortCode;
    private double amount;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFromAcount() {
        return fromAcount;
    }

    public void setFromAcount(int fromAcount) {
        this.fromAcount = fromAcount;
    }

    public int getFromAcountSortCode() {
        return fromAcountSortCode;
    }

    public void setFromAcountSortCode(int fromAcountSortCode) {
        this.fromAcountSortCode = fromAcountSortCode;
    }

    public int getToAcount() {
        return toAcount;
    }

    public void setToAcount(int toAcount) {
        this.toAcount = toAcount;
    }

    public int getToAcountSortCode() {
        return toAcountSortCode;
    }

    public void setToAcountSortCode(int toAcountSortCode) {
        this.toAcountSortCode = toAcountSortCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
