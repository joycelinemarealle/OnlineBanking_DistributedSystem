package com.jaqg.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IllegalRestArgumentException extends RuntimeException {

    public IllegalRestArgumentException(String message) {
        super(message);
    }
}
