package com.booking.theater.controller.open;

import com.booking.theater.data.Movie;
import com.booking.theater.data.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Service
@RequestMapping(path="/movieViewer")
public class MovieViewer {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping
    public @ResponseBody Iterable<Movie> getAllMovie(){
        return movieRepository.findAll();
    }
}
