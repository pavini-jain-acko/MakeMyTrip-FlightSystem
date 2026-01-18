package com.example.makemytrip.controller;

import com.example.makemytrip.dto.request.CreateBookingRequest;
import com.example.makemytrip.dto.response.CreateBookingResponse;
import com.example.makemytrip.dto.response.GetBookingDetailsResponse;
import com.example.makemytrip.entity.Booking;
import com.example.makemytrip.service.BookingService;
import jakarta.validation.Valid;
//TODO PJJ
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO PJJ
@Slf4j
@RestController
public class BookingController {
    public final BookingService bookingService;
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping("/bookings")
    public CreateBookingResponse CreateBooking(
            @Valid @RequestBody CreateBookingRequest createBookingRequest)throws Exception{

        //TODO PJJ
        log.info("Received a Create Booking Request :" + createBookingRequest);
        return bookingService.CreateBooking(createBookingRequest);
    }

    @GetMapping("/bookings")
    public GetBookingDetailsResponse GetBookingDetails(
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
