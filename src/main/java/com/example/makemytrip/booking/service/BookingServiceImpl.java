package com.example.makemytrip.booking.service;

import com.example.makemytrip.Payment.Service.PaymentService;
import com.example.makemytrip.booking.model.Booking;
import com.example.makemytrip.booking.model.BookingStatus;
import com.example.makemytrip.booking.repository.BookingRepository;
import com.example.makemytrip.booking.repository.UserRepository;
import com.example.makemytrip.search.repository.FlightRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class BookingServiceImpl implements BookingService{
    private final PaymentService paymentService;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    BookingServiceImpl(UserRepository userRepository, PaymentService paymentService, BookingRepository bookingRepository, FlightRepository flightRepository){
        this.userRepository = userRepository;
        this.paymentService = paymentService;
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    public boolean CreateBooking(Integer FlightID, Integer UserID, Integer AmountPaid, String PassengerName, Integer PassengerAge, Long PhoneNumber, String EmailID){
        if(!userRepository.existsByUserid(UserID))
            throw new ResponseStatusException(BAD_REQUEST, "Invalid UserID");

        if(!flightRepository.existsByFlightidAndSeatsavailableGreaterThan(FlightID,0))
            throw new ResponseStatusException(BAD_REQUEST, "No Seats Available");

        Booking booking = new Booking();
        booking.setUserid(UserID);
        booking.setAmountpaid(AmountPaid);
        booking.setFlightid(FlightID);
        booking.setPassangerage(PassengerAge);
        booking.setPassengername(PassengerName);
        boolean paymentStatus = paymentService.ProcessPayment();
        if(paymentStatus) {
            booking.setStatus(BookingStatus.CONFIRMED);
            flightRepository.updateSeatsavailableByFlightid(FlightID);
        }
        else
            booking.setStatus(BookingStatus.FAILED);

        bookingRepository.save(booking);

        return paymentStatus;
    }

    @Override
    public List<Booking> GetBookingDetails(Integer BookingID){
        if(!bookingRepository.existsByBookingID(BookingID))
            throw new ResponseStatusException(BAD_REQUEST, "Invalid BookingID");
        return bookingRepository.findByBookingID(BookingID);
    }

    @Override
    public List<Booking> GetBookingByUserID(Integer UserID){
        if(!bookingRepository.existsByUserid(UserID))
            throw new ResponseStatusException(BAD_REQUEST, "Invalid UserID");
        return bookingRepository.findByUserid(UserID);
    }

    @Override
    public boolean CancelBooking(Integer BookingID){

        Booking booking = bookingRepository.findById(BookingID)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Invalid BookingID"));

        if (booking.getStatus() == BookingStatus.CANCELLED)
            throw new ResponseStatusException(BAD_REQUEST, "Booking Already Cancelled");

        bookingRepository.updateStatusByBookingID(BookingID);
        flightRepository.increaseSeatAvailable(booking.getFlightid());

        return true;
    }
}
