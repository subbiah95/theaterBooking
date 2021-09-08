package com.booking.theater.controller.auth;

import com.booking.theater.annotation.Authorized;
import com.booking.theater.data.*;
import com.booking.theater.model.BookingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Service
@RequestMapping(path="/seatBooking",headers = {"Authorization"})
public class SeatBookingController {
    @Autowired
    private MovieShowRepository movieShowRepository;
    @Autowired
    private SeatStatusRepository seatStatusRepository;

    @PostMapping("/open")
    @Authorized
    public @ResponseBody
    String openSeatsForBooking (@RequestParam long showID){
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
