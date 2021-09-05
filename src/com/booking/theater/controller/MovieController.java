package com.booking.theater.controller;

import com.booking.theater.data.Movie;
import com.booking.theater.data.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Controller
@Service
@RequestMapping(path="/movie")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    @PostMapping
    public @ResponseBody String addNewMovie (@RequestParam String name, @RequestParam Integer durationInMinutes){
        Movie movie = new Movie();
        movie.setName(name);
        movie.setDurationInMinutes(durationInMinutes);
        movieRepository.save(movie);
        return "saved";
    }

    @GetMapping
    public @ResponseBody Iterable<Movie> getAllMovie(){
        return movieRepository.findAll();
    }
}
