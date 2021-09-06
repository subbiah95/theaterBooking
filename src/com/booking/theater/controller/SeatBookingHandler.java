package com.booking.theater.controller;

import com.booking.theater.data.*;
import com.booking.theater.model.BookingRequest;
import com.booking.theater.model.BookingTransactionInProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Service
@RequestMapping(path="/seatBooking")
public class SeatBookingHandler {
    @Autowired
    private MovieShowRepository movieShowRepository;
    @Autowired
    private SeatStatusRepository seatStatusRepository;

    @PostMapping("/open")
    public @ResponseBody String openSeatsForBooking (@RequestParam long showID){
        movieShowRepository.findById(showID).ifPresent( movieShow -> {
            int totalSeats = movieShow.cinemaHall.getSeatingCapacity();
            for(int i = 1 ; i <= totalSeats; i++){
                SeatStatus seatStatus = new SeatStatus();
                seatStatus.setSeatNo(i);
                seatStatus.setMovieShow(movieShow);
                seatStatus.setStatus(BookingStatus.AVAILABLE);
                seatStatusRepository.save(seatStatus);
            }
        });
        return "saved";
    }

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
}
