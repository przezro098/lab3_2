package edu.iis.mto.time;

import org.joda.time.Duration;
import org.joda.time.Instant;

import org.junit.jupiter.api.*;

class OrderTest {

    private static Order order;

    @BeforeAll public static void initialization() {
        order = new Order();
        order.addItem(new OrderItem());
    }

    public Instant makeInstant(int numberOfHours) {
        return Instant.now().plus(Duration.standardHours(numberOfHours));
    }

}
