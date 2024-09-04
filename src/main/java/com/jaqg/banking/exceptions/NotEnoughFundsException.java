package com.jaqg.banking.exceptions;

public class NotEnoughFundsException extends RuntimeException {
    public NotEnoughFundsException(Long number) {
        super("Account with number " + number + " has not enough funds");
    }
}
