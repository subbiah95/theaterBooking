package com.booking.theater.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "cinema_hall_id", "startTime"}) })
public class MovieShow {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Setter @Getter long id;

    @OneToOne(targetEntity = Movie.class, orphanRemoval = true)
    @JoinColumn(referencedColumnName = "id")
    @Setter @Getter public Movie movie;

    @OneToOne(targetEntity = CinemaHall.class, orphanRemoval = true)
    @JoinColumn(referencedColumnName = "id")
    @Setter @Getter public CinemaHall cinemaHall;

    @Column(nullable = false)
    @Setter @Getter long startTime;
}
