package com.example.makemytrip.booking.controller;

import com.example.makemytrip.booking.model.Booking;
import com.example.makemytrip.booking.repository.BookingRepository;
import com.example.makemytrip.booking.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class BookingController {
    public final BookingService bookingService;
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }
    @PostMapping("/bookings")
    public boolean CreateBooking(
            @RequestParam(required = true) Integer FlightID,
            @RequestParam(required = true) Integer UserID,
            @RequestParam(required = true) Integer AmountPaid,
            @RequestParam(required = true) String PassengerName,
            @RequestParam(required = true) Integer PassengerAge,
            @RequestParam(required = true) Long PhoneNumber,
            @RequestParam(required = true) String EmailID
    )throws Exception{
        return bookingService.CreateBooking(FlightID, UserID, AmountPaid, PassengerName, PassengerAge, PhoneNumber, EmailID);
    }
    @GetMapping("/bookings")
    public List<Booking> GetBookingDetails(
            @RequestParam(required = true) Integer BookingID
    )throws Exception{
        return bookingService.GetBookingDetails(BookingID);
    }

    @GetMapping("/bookings/userid")
    public List<Booking> GetBookingByUserID(
            @RequestParam(required = true) Integer UserID
    )throws Exception{
        return bookingService.GetBookingByUserID(UserID);
    }

    @PutMapping("/bookings")
    public boolean CancelBooking(
            @RequestParam(required = true) Integer BookingID
    )throws Exception{
        return bookingService.CancelBooking(BookingID);
    }
}
