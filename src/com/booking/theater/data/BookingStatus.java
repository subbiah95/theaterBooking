package com.booking.theater.data;

public enum BookingStatus {
    BOOKED,
    AVAILABLE,
    /**
     * Temporarily hold seat while in payment Transaction
     */
    ON_HOLD
}
