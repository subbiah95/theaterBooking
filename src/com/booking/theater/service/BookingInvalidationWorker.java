package com.booking.theater.service;

import com.booking.theater.data.*;
import com.booking.theater.model.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Scope("prototype")
public class BookingInvalidationWorker implements Runnable{
    @Autowired
    private MovieShowRepository movieShowRepository;
    @Autowired
    private SeatStatusRepository seatStatusRepository;
    @Autowired
    private BookingDetailsRepository bookingDetailsRepository;

    @SneakyThrows
    public void run()
    {
        BookingTransactionOnHold bookingTransactionOnHold = BookingTransactionOnHold.getInstance();
        while(true){
            //lock the mutex
            BookingTransactionInProgress.monitor.enter();

            try{
                //add transactions with same timestamp to List
                bookingTransactionOnHold.peekQueue().ifPresent(new Consumer<BookingRequest>() {
                    @Override public void accept(BookingRequest bookingRequest) {
                            if (bookingRequest.getRequestTime() + bookingTransactionOnHold.timeOut > System.currentTimeMillis())
                                return;
                            bookingRequest = bookingTransactionOnHold.retrieveFromQueue().get();
                            bookingDetailsRepository.updatePaymentStatus(bookingRequest.getBookingId(), PaymentStatus.FAIL);

                            //Check if payment Status has failed, if failed free seats
                            System.out.println("Check if payment Status has failed, if failed free seats");
                            if(bookingDetailsRepository.getPaymentStatus(bookingRequest.getBookingId()).equals(PaymentStatus.FAIL)){
                                for( Integer seatNo : bookingRequest.getSeat()){
                                    seatStatusRepository.updateSeatStatus(bookingRequest.getMovieShowID(), seatNo, BookingStatus.AVAILABLE);
                                }
                            }
                        }
                    });
            }catch (Exception e){
                System.out.println(e);
            }finally {
                //unlock the mutex
                BookingTransactionInProgress.monitor.leave();
            }

            Thread.sleep(bookingTransactionOnHold.peekQueue().isPresent() ? 0 : 100);
        }
    }
}
