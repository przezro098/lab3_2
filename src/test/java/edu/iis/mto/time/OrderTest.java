package edu.iis.mto.time;

import org.junit.jupiter.api.BeforeAll;

class OrderTest {

    private Order order;

    @BeforeAll public void initialization() {
        order = new Order();
        order.addItem(new OrderItem());
    }

}
