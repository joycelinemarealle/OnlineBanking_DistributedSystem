package com.jaqg.banking.DTO;

import com.jaqg.banking.entities.Transaction;

import java.util.List;

public class CreateAccountResponseDTO {
    private Long number;
    private Long sortCode;
    private double openingBalance;
    private List< Transaction> transcations;
    private Long customer;

    public CreateAccountResponseDTO(Long number, Long sortCode, List<Transaction> transcations, double openingBalance, Long customer) {
        this.number = number;
        this.sortCode = sortCode;
        this.transcations = transcations;
        this.openingBalance = openingBalance;
        this.customer = customer;
    }



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

    public double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(double openingBalance) {
        this.openingBalance = openingBalance;
    }

    public Integer[] getTranscations() {
        return transcations;
    }

    public void setTranscations(Integer[] transcations) {
        this.transcations = transcations;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }
}
