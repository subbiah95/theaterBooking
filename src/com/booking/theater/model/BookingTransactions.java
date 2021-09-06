package com.booking.theater.model;

import com.google.common.util.concurrent.Monitor;
import lombok.NonNull;

import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BookingTransactions {

    /**
     * Consider this as Kafka or some other Messaging queue.
     */
    private final ConcurrentLinkedQueue<BookingRequest> clq;
    public static Monitor monitor = new Monitor();

    public BookingTransactions(){
        clq = new ConcurrentLinkedQueue<BookingRequest>();
    }

    public void addBookingToService(@NonNull BookingRequest bookingRequest){
        clq.add(bookingRequest);
    }

    public Optional<BookingRequest> peekQueue(){
        return monitor.isOccupied() ? Optional.ofNullable(clq.peek()) : Optional.empty();
    }

    public Optional<BookingRequest> retrieveFromQueue(){
        return monitor.isOccupied() ? Optional.ofNullable(clq.remove()) : Optional.empty();
    }
}

