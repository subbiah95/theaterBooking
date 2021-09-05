package com.booking.theater.data;

public enum PaymentStatus {
    SUCCESS,
    FAIL,
    /**
     * payment is yet to be confirmed from payment gateway
     */
    INPROGRESS
}
