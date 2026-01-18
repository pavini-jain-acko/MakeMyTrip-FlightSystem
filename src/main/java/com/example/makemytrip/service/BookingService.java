package com.example.makemytrip.service;

import com.example.makemytrip.dto.request.CreateBookingRequest;
import com.example.makemytrip.dto.response.CreateBookingResponse;
import com.example.makemytrip.dto.response.GetBookingDetailsResponse;
import com.example.makemytrip.entity.Booking;

import java.util.List;

public interface BookingService {
    public CreateBookingResponse CreateBooking(CreateBookingRequest createBookingRequest);
    public GetBookingDetailsResponse GetBookingDetails(Integer BookingID);
    public List<Booking> GetBookingByUserID(Integer UserID);
    public boolean CancelBooking(Integer BookingID);
}
