package edu.iis.mto.time;

import org.joda.time.Duration;
import org.joda.time.Instant;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

class OrderTest {

    private Order order;

    @BeforeAll public void initialization() {
        order = new Order();
        order.addItem(new OrderItem());
    }

    public Instant makeInstant(int numberOfHours) {
        return Instant.now().plus(Duration.standardHours(numberOfHours));
    }

}
