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

    @Test @DisplayName("Check whether order will change state to expired after 24 hours")
    public void checkIfOrderWillBeExpiredAfterSpecifiedTimeTest() {

        order.submit();
        order.makeInstant(makeInstant(25));
        Assertions.assertThrows(OrderExpiredException.class, () -> order.confirm());
        Assertions.assertEquals(Order.State.CANCELLED, order.getOrderState());
    }

    @Test @DisplayName("Check whether order won't be expired after 24 hours")
    public void checkIfOrderWontBeExpiredAfterLessThan24hTest() {

        order.submit();
        order.makeInstant(makeInstant(10));
        order.confirm();
        Assertions.assertEquals(Order.State.CONFIRMED, order.getOrderState());
    }

    @Test @DisplayName("Check whether order will be realized without confirmation")
    public void checkIfOrderWillBeRealizedInSubmittedStateTest() {

        order.submit();
        order.makeInstant(makeInstant(10));
        Assertions.assertThrows(OrderStateException.class, () -> order.realize());
        Assertions.assertEquals(Order.State.SUBMITTED, order.getOrderState());

    }





}
