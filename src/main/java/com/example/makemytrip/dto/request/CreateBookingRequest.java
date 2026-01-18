package com.example.makemytrip.dto.request;


import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;

@Data
public class CreateBookingRequest {

    @NotNull(message = "Flight ID is required")
    private Integer flightId;

    @NotNull(message = "User ID is required")
    private Integer userId;

    @NotNull(message = "Amount Paid is required")
    @Min(value = 0, message = "Amount cannot be negative")
    private Integer amountPaid;

    @NotBlank(message = "Passenger Name is required")
    private String passengerName;

    @NotNull(message = "Passenger Age is required")
    private Integer passengerAge;

    @NotNull(message = "Phone Number is required")
    private Long phoneNumber;

    @NotBlank(message = "Email ID is required")
    private String emailId;
}
