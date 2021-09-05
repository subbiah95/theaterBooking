package com.booking.theater.data;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter @Getter private long id;

    @Column(nullable = false)
    @Setter @Getter private String name;

    @Column(nullable = false)
    @Setter @Getter private Integer durationInMinutes;
}
