package com.booking.theater.service;

import com.booking.theater.data.*;
import com.booking.theater.model.BookingRequest;
import com.booking.theater.model.BookingStatus;
import com.booking.theater.model.BookingTransactionOnHold;
import com.booking.theater.model.PaymentStatus;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookingProcessor {
    @Autowired
    private MovieShowRepository movieShowRepository;
    @Autowired
    private SeatStatusRepository seatStatusRepository;
    @Autowired
    private BookingDetailsRepository bookingDetailsRepository;

    @Setter
    private List<BookingRequest> bookingRequests;

    public BookingProcessor() {
    }

    public void setInitialTransaction(BookingRequest bookingRequest){
        BookingDetails bookingDetails = new BookingDetails();
        movieShowRepository.findById(bookingRequest.getMovieShowID()).ifPresent(bookingDetails::setMovieShow);
        bookingDetails.setTransactionId(bookingRequest.getBookingId());
        bookingDetails.setStatus(PaymentStatus.INPROGRESS);

        bookingDetails.setSeat1(bookingRequest.getSeat().get(0));
        if( bookingRequest.getSeat().size() >= 2 ) bookingDetails.setSeat2(bookingRequest.getSeat().get(1));
        if( bookingRequest.getSeat().size() >= 3 ) bookingDetails.setSeat3(bookingRequest.getSeat().get(2));
        if( bookingRequest.getSeat().size() >= 4 ) bookingDetails.setSeat4(bookingRequest.getSeat().get(3));
        if( bookingRequest.getSeat().size() >= 5 ) bookingDetails.setSeat5(bookingRequest.getSeat().get(4));
        if( bookingRequest.getSeat().size() >= 6 ) bookingDetails.setSeat6(bookingRequest.getSeat().get(5));

        bookingDetailsRepository.save(bookingDetails);
    }

    public void processBooking(){

        for(BookingRequest bookingRequest : bookingRequests){
            setInitialTransaction(bookingRequest);
            boolean seatsAvailable = true;
            List<SeatStatus> seatStatuses = new ArrayList<>();
            for(Integer seatNo : bookingRequest.getSeat()) {
                seatStatuses.add(seatStatusRepository.getSeatStatusByShowIdAndSeatNo(bookingRequest.getMovieShowID(), seatNo));
            }
            for(SeatStatus seatStatus : seatStatuses)
                seatsAvailable &= BookingStatus.AVAILABLE.equals(seatStatus.getStatus());

            if(seatsAvailable){
                System.out.println("All seats available");
                for(SeatStatus seatStatus : seatStatuses){
                    seatStatusRepository.updateSeatStatus( seatStatus.getMovieShow().getId(), seatStatus.getSeatNo(), BookingStatus.ON_HOLD);
                }
                BookingTransactionOnHold.getInstance().addBookingToService(bookingRequest);
            }else{
                System.out.println("Seats un-available");
            }
        }
    }
}
