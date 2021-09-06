package com.booking.theater.model;

public class BookingTransactionInProgress extends BookingTransactions{

    private static BookingTransactionInProgress instance;

    private BookingTransactionInProgress(){
        super();
    }

    public static BookingTransactionInProgress getInstance(){
        if( instance == null){
            instance = new BookingTransactionInProgress();
        }
        return instance;
    }
}
