package com.example.makemytrip.service.impl;

import com.example.makemytrip.dto.request.CreateBookingRequest;
import com.example.makemytrip.dto.response.CreateBookingResponse;
import com.example.makemytrip.dto.response.GetBookingDetailsResponse;
import com.example.makemytrip.service.BookingService;
import com.example.makemytrip.service.PaymentService;
import com.example.makemytrip.entity.Booking;
import com.example.makemytrip.enums.BookingStatus;
import com.example.makemytrip.repository.BookingRepository;
import com.example.makemytrip.repository.UserRepository;
import com.example.makemytrip.repository.FlightRepository;
import jakarta.validation.constraints.Null;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

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
    //TODD PJJ remove all params and just pass CreateBookingRequest
    public CreateBookingResponse CreateBooking(CreateBookingRequest createBookingRequest){


    String lockId = ("Flight-" + createBookingRequest.getFlightId());
        synchronized (lockId) {

            CreateBookingResponse createBookingResponse = new CreateBookingResponse();
            try {
                //TODO PJJ


                if(!userRepository.existsByUserid(createBookingRequest.getUserId())) {

                    log.warn("No user exists for this userID " + createBookingRequest.getUserId());
                    throw new ResponseStatusException(BAD_REQUEST, "Invalid UserID");
                }


                if(!flightRepository.existsByFlightidAndSeatsavailableGreaterThan(createBookingRequest.getFlightId(), 0)) {

                    log.info("No seats available for create booking request " + createBookingRequest);
                    createBookingResponse.setBookingStatus(BookingStatus.FAILED);
                    createBookingResponse.setMessage("No Seats Available on the requested Flight");
                }




                Booking booking = new Booking();
                booking.setUserid(createBookingRequest.getUserId());
                booking.setAmountpaid(createBookingRequest.getAmountPaid());
                booking.setFlightid(createBookingRequest.getFlightId());
                booking.setPassangerage(createBookingRequest.getPassengerAge());
                booking.setPassengername(createBookingRequest.getPassengerName());


                boolean paymentStatus = paymentService.ProcessPayment();
                if(paymentStatus) {
                    booking.setStatus(BookingStatus.CONFIRMED);

                    flightRepository.updateSeatsavailableByFlightid(createBookingRequest.getFlightId());


                    //TODO PJJ
                    createBookingResponse.setBookingStatus(BookingStatus.CONFIRMED);
                    createBookingResponse.setMessage("Your Flight Booking is Done!!");
                } else {
                    booking.setStatus(BookingStatus.FAILED);
                    createBookingResponse.setBookingStatus(BookingStatus.FAILED);
                    createBookingResponse.setMessage("Payment Failed, Please try again later!!");

                }


                bookingRepository.save(booking);

                return createBookingResponse;
            } catch (Exception e) {
                log.error("Exception for Create Bookings Request: " + createBookingRequest + " : " + e.getMessage(), e);
                createBookingResponse.setBookingStatus(BookingStatus.FAILED);
                createBookingResponse.setMessage("Internal Server Error, Please try again later");

                return createBookingResponse;
            }
        }
    }

    @Override
    public GetBookingDetailsResponse GetBookingDetails(Integer BookingID){
        if(!bookingRepository.existsByBookingID(BookingID))
            throw new ResponseStatusException(BAD_REQUEST, "Invalid BookingID");
        List<Booking> bookingsByBookingId =  bookingRepository.findByBookingID(BookingID);
        GetBookingDetailsResponse getBookingDetailsResponse = new GetBookingDetailsResponse();
        getBookingDetailsResponse.setBookingList(bookingsByBookingId);
        return getBookingDetailsResponse;
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
