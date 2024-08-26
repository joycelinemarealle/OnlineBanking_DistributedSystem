package com.jaqg.banking.dto;

import java.time.LocalDate;

public class TransactionResponse extends TransactionRequest{
    private LocalDate time;


    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }
}
