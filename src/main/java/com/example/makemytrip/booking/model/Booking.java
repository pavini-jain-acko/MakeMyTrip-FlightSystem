package com.example.makemytrip.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookingdetails")
public class Booking {
    @Id
    @Column(name = "bookingid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingID;

    @Column(name = "userid")
    private Integer userid;

    @Column(name = "flightid")
    private Integer flightid;

    @Column(name = "amountpaid")
    private Integer amountpaid;

    @Column(name = "passengername")
    private String passengername;

    @Column(name = "passengerage")
    private Integer passangerage;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookingStatus status;
}
