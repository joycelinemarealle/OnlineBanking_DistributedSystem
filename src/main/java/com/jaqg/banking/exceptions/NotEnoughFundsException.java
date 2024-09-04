package com.jaqg.banking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class NotEnoughFundsException extends RuntimeException {
    public NotEnoughFundsException(Long number) {
        super("Account with number " + number + " has not enough funds");
    }
}
