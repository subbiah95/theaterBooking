package com.booking.theater.controller.open;

import com.booking.theater.data.MovieShow;
import com.booking.theater.data.MovieShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Service
@RequestMapping(path="/movieShow")
public class MovieShowViewer {

    @Autowired
    private MovieShowRepository movieShowRepository;

    @GetMapping
    public @ResponseBody Iterable<MovieShow> getAllMovie(){
        return movieShowRepository.findAll();
    }
}
