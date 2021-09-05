package com.booking.theater.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class BookingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter @Getter private long id;

    @OneToOne(targetEntity = MovieShow.class)
    @JoinColumn(referencedColumnName = "id")
    @Getter @Setter private MovieShow movieShow;

    @Column(nullable = false)
    @Setter @Getter Integer seat1;
    @Setter @Getter Integer seat2;
    @Setter @Getter Integer seat3;
    @Setter @Getter Integer seat4;
    @Setter @Getter Integer seat5;
    @Setter @Getter Integer seat6;

    @Column(nullable = false, unique=true)
    @Setter @Getter private long transactionId;

    @Column(nullable = false)
    @Setter @Getter private PaymentStatus status;
}
