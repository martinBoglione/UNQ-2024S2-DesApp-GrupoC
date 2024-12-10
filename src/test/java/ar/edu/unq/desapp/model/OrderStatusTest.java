package ar.edu.unq.desapp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderStatusTest {

    @Test
    void thereAreOnlyFiveOrderStatus() {
        assertEquals(5, OrderStatus.values().length);
    }

    @Test
    void eachOrderStatusCanBeInstantiatedByItsName() {
        assertNotNull(OrderStatus.valueOf("ACTIVE"));
        assertNotNull(OrderStatus.valueOf("INPROCESS"));
        assertNotNull(OrderStatus.valueOf("FILLED"));
        assertNotNull(OrderStatus.valueOf("CANCELLED_BY_SYSTEM"));
        assertNotNull(OrderStatus.valueOf("CANCELLED_BY_USER"));
    }
}