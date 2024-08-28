package com.jaqg.banking.entities;

import com.jaqg.banking.exceptions.NotAllowedOperationType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OperationTypeTest {

    @ParameterizedTest
    @EnumSource(OperationType.class)
    void testValueOf(OperationType type) {
        OperationType operationType = OperationType.valueOf(type.id());
        assertThat(operationType).isEqualTo(type);
    }

    @Test
    void testValueOf_typeExistingTypeId_throwsNullPointerException() {
        assertThrows(NotAllowedOperationType.class, () -> OperationType.valueOf(5));
    }
}