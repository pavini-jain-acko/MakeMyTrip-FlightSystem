package com.example.makemytrip.dto.response;

import com.example.makemytrip.enums.BookingStatus;
import lombok.Data;

@Data
public class CreateBookingResponse {

    private BookingStatus bookingStatus;
    private String message;
}
