package com.example.makemytrip.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="FlightDetails")
public class Flight{

    @Id
    @Column(name="flightid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flightid;

    @Column(name="Source")
    private String source;

    @Column(name="Destination")
    private String destination;

    @Column(name="DepartureDate")
    private LocalDateTime departureDate;

    @Column(name="ArrivalDate")
    private LocalDateTime arrivalDate;

    @Column(name="seatsavailable")
    private Integer seatsavailable;
}
