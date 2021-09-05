package com.booking.theater.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaHallRepository extends CrudRepository<CinemaHall, Long> {
}
