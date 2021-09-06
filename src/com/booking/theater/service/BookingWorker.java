package com.booking.theater.service;

import com.booking.theater.model.BookingRequest;
import com.booking.theater.model.BookingTransactionInProgress;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Consumer;

@Component
@Scope("prototype")
public class BookingWorker implements Runnable{
    @Autowired
    AutowireCapableBeanFactory autowireCapableBeanFactory;

    Optional<BookingRequest> getValueFromQueueOnMatch(BookingTransactionInProgress bookingTransaction, BookingRequest prevBookingRequest){
        final boolean[] isConcurrentBooking = {false};
        bookingTransaction.peekQueue().ifPresent(new Consumer<BookingRequest>() {
            @Override public void accept(BookingRequest bookingRequest) {
                isConcurrentBooking[0] = prevBookingRequest.getRequestTime().equals(bookingRequest.getRequestTime());
            }
        });
        return isConcurrentBooking[0] ? bookingTransaction.retrieveFromQueue() : Optional.empty();
    }

    @SneakyThrows
    public void run()
    {
        BookingTransactionInProgress bookingTransaction = BookingTransactionInProgress.getInstance();
        while(true){

            List<BookingRequest> bookingRequests = new ArrayList<>();

            //lock the mutex
            BookingTransactionInProgress.monitor.enter();

            try{
                //add transactions with same timestamp to List
                bookingTransaction.peekQueue().ifPresent(new Consumer<BookingRequest>() {
                    @Override public void accept(BookingRequest bookingRequest) {
                        System.out.println("Booking is Present");
                        bookingTransaction.retrieveFromQueue().ifPresent(bookingRequests::add);
                        Optional<BookingRequest> next;
                        do{
                            next = getValueFromQueueOnMatch(bookingTransaction, bookingRequest);
                            next.ifPresent(bookingRequests::add);
                        }while(next.isPresent());
                    }
                });
            }catch (Exception e){
                System.out.println(e);
            }finally {
                //unlock the mutex
                BookingTransactionInProgress.monitor.leave();
            }

            //Sort list based on seats reserved in descending order
            Collections.sort(bookingRequests, Comparator.comparingInt(BookingRequest::getTotalSeats).reversed());

            BookingProcessor bookingProcessor = new BookingProcessor();
            autowireCapableBeanFactory.autowireBean(bookingProcessor);
            bookingProcessor.setBookingRequests(bookingRequests);
            bookingProcessor.processBooking();

            Thread.sleep(bookingTransaction.peekQueue().isPresent() ? 0 : 100);
        }
    }
}
