package com.jaqg.banking.entities;

import com.jaqg.banking.exeptions.NotAllowedOperationType;

public enum OperationType {
    TRANSFER(0),
    WITHDRAWAL(1),
    DEPOSIT(2);

    private final Integer id;

    OperationType(int id) {
        this.id = id;
    }

    public static OperationType valueOf(int id) {
        for (OperationType value : values()) {
            if (value.id == id) {
                return value;
            }
        }
        throw new NotAllowedOperationType();
    }

    public Integer id() {
        return id;
    }


}
