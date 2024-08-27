package com.jaqg.banking.exceptions;

public class NotAllowedOperationType extends RuntimeException {
    public NotAllowedOperationType() {
        super("Not allowed operation type");
    }
}
