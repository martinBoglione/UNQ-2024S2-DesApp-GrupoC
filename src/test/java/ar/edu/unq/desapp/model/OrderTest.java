package ar.edu.unq.desapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void instanceCreationReturnsActiveOrder() {
        User user = User.builder().build();
        Order order = Order.builder()
                .asset(CryptoAsset.BNBUSDT)
                .quantity(1000)
                .price(152.25)
                .amountArs(152250)
                .user(user)
                .operationType(OperationType.BUY)
                .build();

        assertNotNull(order);
        assertEquals(user, order.getUser());
        assertSame(OrderStatus.ACTIVE, order.getStatus());

    }
}