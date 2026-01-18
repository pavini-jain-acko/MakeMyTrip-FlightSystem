package com.example.makemytrip.dto.response;

import com.example.makemytrip.entity.Booking;
import lombok.Data;

import java.util.List;

@Data
public class GetBookingDetailsResponse {
    private List<Booking> bookingList;
}
