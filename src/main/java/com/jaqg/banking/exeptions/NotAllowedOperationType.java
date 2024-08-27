package com.jaqg.banking.exeptions;

public class NotAllowedOperationType extends RuntimeException {
    public NotAllowedOperationType() {
        super("Not allowed operation type");
    }
}
