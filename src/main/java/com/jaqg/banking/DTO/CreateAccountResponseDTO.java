package com.jaqg.banking.DTO;

public class CreateAccountResponseDTO {
    private Long number;
    private Long sortCode;
    private double openingBalance;
    private Integer [] transcations;
    private Integer customer;

    public CreateAccountResponseDTO(Long number, Long sortCode, double openingBalance, Integer[] transcations, Integer customer) {
        this.number = number;
        this.sortCode = sortCode;
        this.openingBalance = openingBalance;
        this.transcations = transcations;
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
