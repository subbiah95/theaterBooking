package com.booking.theater.data;

import com.booking.theater.model.BookingStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "movie_show_id", "seatNo"}) })
public class SeatStatus {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Setter @Getter long id;

    @OneToOne(targetEntity = MovieShow.class, orphanRemoval = true)
    @JoinColumn(referencedColumnName = "id")
    @Getter @Setter private MovieShow movieShow;

    @Column(nullable = false)
    @Getter @Setter private Integer seatNo;

    @Column(nullable = false)
    @Getter @Setter private BookingStatus status;
}
