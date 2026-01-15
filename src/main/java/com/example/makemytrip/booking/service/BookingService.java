package com.example.makemytrip.booking.service;

import com.example.makemytrip.booking.model.Booking;

import java.util.List;

public interface BookingService {
    public boolean CreateBooking(Integer FlightID, Integer UserID, Integer AmountPaid, String PassengerName, Integer PassengerAge, Long PhoneNumber, String EmailID);
    public List<Booking> GetBookingDetails(Integer BookingID);
    public List<Booking> GetBookingByUserID(Integer UserID);
    public boolean CancelBooking(Integer BookingID);
}
