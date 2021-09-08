package com.booking.theater.controller.open;

import com.booking.theater.data.*;
import com.booking.theater.model.BookingRequest;
import com.booking.theater.model.BookingTransactionInProgress;
import com.booking.theater.model.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Service
@RequestMapping(path="/seatBookingHandler")
public class SeatBookingHandler {
    @Autowired
    private SeatStatusRepository seatStatusRepository;
    @Autowired
    private BookingDetailsRepository bookingDetailsRepository;


    @GetMapping("/seatStatus")
    public @ResponseBody
    List<SeatStatus> getSeatStatusForShowById (@RequestParam long showID){
        return seatStatusRepository.getSeatStatusByShowId(showID);
    }

    @PostMapping("/request")
    public @ResponseBody Long requestBooking(@RequestBody BookingRequest bookingRequest){
        BookingTransactionInProgress.getInstance().addBookingToService(bookingRequest);
        return bookingRequest.getBookingId();
    }

    @GetMapping("/bookingStatus")
    public @ResponseBody
    PaymentStatus bookingStatus(@RequestParam long bookingId){
        return bookingDetailsRepository.getPaymentStatus(bookingId);
    }
}
