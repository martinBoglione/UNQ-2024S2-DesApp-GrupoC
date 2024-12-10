package ar.edu.unq.desapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationTypeTest {

    @Test
    void thereAreOnlyTwoOperationTypes() {
        assertEquals(2, OperationType.values().length);
    }

    @Test
    void eachOperationTypeCanBeInstantiatedByItsName() {
        assertNotNull(OperationType.valueOf("BUY"));
        assertNotNull(OperationType.valueOf("SELL"));
    }
}