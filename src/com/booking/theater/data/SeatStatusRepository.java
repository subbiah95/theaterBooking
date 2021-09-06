package com.booking.theater.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SeatStatusRepository extends JpaRepository<SeatStatus, Long> {

    @Query("SELECT b FROM SeatStatus b where movie_show_id = :showId")
    public List<SeatStatus> getSeatStatusByShowId(@Param("showId") long showId);

    @Query("SELECT b FROM SeatStatus b where movie_show_id = :showId and seat_no = :seatNo")
    public SeatStatus getSeatStatusByShowIdAndSeatNo(@Param("showId") long showId, @Param("seatNo") Integer seatNo);

    @Transactional
    @Modifying
    @Query("UPDATE SeatStatus b  SET status = :status where movie_show_id = :showId AND seat_no = :seatNo")
    public void updateSeatStatus(@Param("showId") long showId, @Param("seatNo") Integer seatNo, @Param("status") BookingStatus status);
}
