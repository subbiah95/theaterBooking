package com.booking.theater.controller.auth;

import com.booking.theater.annotation.Authorized;
import com.booking.theater.data.CinemaHallRepository;
import com.booking.theater.data.MovieRepository;
import com.booking.theater.data.MovieShow;
import com.booking.theater.data.MovieShowRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Controller
@Service
@RequestMapping(path="/movieShow",headers = {"Authorization"})
public class MovieShowController {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CinemaHallRepository cinemaHallRepository;
    @Autowired
    private MovieShowRepository movieShowRepository;

    @PostMapping
    @Authorized
    public @ResponseBody String addNewMovie (@RequestParam long movieId, @RequestParam long hallId, @RequestParam long startTime){
        long id;
        MovieShow movieShow = new MovieShow();
        movieRepository.findById(movieId).ifPresent(movieShow::setMovie);
        cinemaHallRepository.findById(hallId).ifPresent(movieShow::setCinemaHall);
        movieShow.setStartTime(startTime);
        id = movieShow.getId();
        movieShowRepository.save(movieShow);
        return "saved";
    }
}