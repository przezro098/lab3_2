package edu.iis.mto.time;

import org.joda.time.DateTime;

import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

class OrderTest {

    private static Order order;
    private static MockClock mockClock;
    private static DateTime time;

    @BeforeEach public void initialization() {
        mockClock = new MockClock();
        time = new DateTime();
        order = new Order(mockClock);
        mockClock.setTime(time);
        order.addItem(new OrderItem());
    }

    @Test @DisplayName("Check whether order will change state to cancelled after 24 hours")
    public void checkIfOrderWillBeExpiredAfterSpecifiedTimeTest() {
        order.submit();
        mockClock.setTime(time.plusHours(30));
        Assertions.assertThrows(OrderExpiredException.class, () -> order.confirm());
        Assertions.assertEquals(Order.State.CANCELLED, order.getOrderState());
    }

    @Test @DisplayName("Check whether order will change state to expired after 24 hours")
    public void checkIfOrderWillBeExpiredAfterSpecifiedTimeSecondTest() {

        order.submit();
        mockClock.setTime(time.plusHours(26));
        Assertions.assertThrows(OrderExpiredException.class, () -> order.confirm());
        Assertions.assertEquals(Order.State.CANCELLED, order.getOrderState());
    }

    @Test @DisplayName("Check whether order won't be expired after less than 24 hours") public void checkIfOrderWontBeExpiredAfterLessThan24hTest() {

        order.submit();
        mockClock.setTime(time.plusHours(10));
        order.confirm();
        Assertions.assertEquals(Order.State.CONFIRMED, order.getOrderState());
    }

    @Test @DisplayName("Check whether order will be realized without confirmation")
    public void checkIfOrderWillBeRealizedInSubmittedStateTest() {

        order.submit();
        mockClock.setTime(time.plusHours(10));
        Assertions.assertThrows(OrderStateException.class, () -> order.realize());
        Assertions.assertEquals(Order.State.SUBMITTED, order.getOrderState());

    }

}
