package com.booking.theater.model;

public enum BookingStatus {
    BOOKED,
    AVAILABLE,
    /**
     * Temporarily hold seat while in payment Transaction
     */
    ON_HOLD,
    CANCELLED
}
