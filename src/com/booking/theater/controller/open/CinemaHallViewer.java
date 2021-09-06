package com.booking.theater.controller.open;

import com.booking.theater.data.CinemaHall;
import com.booking.theater.data.CinemaHallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Service
@RequestMapping(path="/cinemaHallViewer")
public class CinemaHallViewer {
    @Autowired
    private CinemaHallRepository cinemaHallRepository;

    @GetMapping
    public @ResponseBody Iterable<CinemaHall> getAllCinemaHall(){
        return cinemaHallRepository.findAll();
    }
}
