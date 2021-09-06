package com.booking.theater.controller.auth;

import com.booking.theater.annotation.Authorized;
import com.booking.theater.data.CinemaHall;
import com.booking.theater.data.CinemaHallRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Controller
@Service
@RequestMapping(path="/cinemaHall",headers = {"Authorization"})
public class CinemaHallController {

    @Autowired
    private CinemaHallRepository cinemaHallRepository;

    @PostMapping
    @Authorized
    public @ResponseBody String addNewCinemaHall (@RequestParam String name, @RequestParam Integer seatingCapacity){
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setName(name);
        cinemaHall.setSeatingCapacity(seatingCapacity);
        cinemaHallRepository.save(cinemaHall);
        return "saved";
    }

    @PutMapping("/seatingCapacity")
    @Authorized
    public @ResponseBody String updateCinemaHallSeatingCapacity(@RequestParam long id, @NonNull @RequestParam Integer seatingCapacity){
        cinemaHallRepository.findById(id).ifPresent( cinemaHall ->{
            cinemaHall.setSeatingCapacity(seatingCapacity);
            cinemaHallRepository.save(cinemaHall);
        });
        return "saved";
    }

    @PutMapping("/name")
    @Authorized
    public @ResponseBody String updateCinemaHallName(@RequestParam long id, @NonNull @RequestParam String name){
        cinemaHallRepository.findById(id).ifPresent( cinemaHall -> {
            cinemaHall.setName(name);
            cinemaHallRepository.save(cinemaHall);
        });
        return "saved";
    }
}
