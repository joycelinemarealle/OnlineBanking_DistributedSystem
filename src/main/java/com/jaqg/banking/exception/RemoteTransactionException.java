package com.jaqg.banking.exception;

public class RemoteTransactionException extends RuntimeException {
    public RemoteTransactionException(String message) {
        super(message);
    }

    public RemoteTransactionException(String message, Throwable e) {
        super(message, e);
    }
}
