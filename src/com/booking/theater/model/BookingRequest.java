package com.booking.theater.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class BookingRequest {

    private static final AtomicLong counter = new AtomicLong();

    @Setter
    @Getter
    private Long movieShowID;

    @Setter
    @Getter
    private List<Integer> seat;

    @Getter
    private Long requestTime;

    @Getter
    private Long bookingId;

    public BookingRequest(){
        requestTime = System.currentTimeMillis();
        bookingId = counter.getAndIncrement();
    }

    public int getTotalSeats(){
        return seat.size();
    }
}
