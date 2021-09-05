package com.booking.theater.data;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class CinemaHall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter @Getter private long id;

    @Column(nullable = false)
    @Setter @Getter private String name;

    @Column(nullable = false)
    @Setter @Getter private Integer seatingCapacity;
}
