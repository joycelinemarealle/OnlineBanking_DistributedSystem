package com.jaqg.banking.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException(Long number) {
        super("Account with number [" + number + "] not found");
    }
}
