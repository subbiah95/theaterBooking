package com.booking.theater.controller;

import com.booking.theater.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Controller
@Service
@RequestMapping(path="/seatbooking")
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
}
