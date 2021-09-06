package com.booking.theater.model;

public class BookingTransactionOnHold extends BookingTransactions{

    private static BookingTransactionOnHold instance;
    public static final long timeOut = 2 * 60 * 1000; // 2 minutes

    private BookingTransactionOnHold(){
        super();
    }

    public static BookingTransactionOnHold getInstance(){
        if( instance == null){
            instance = new BookingTransactionOnHold();
        }
        return instance;
    }
}
