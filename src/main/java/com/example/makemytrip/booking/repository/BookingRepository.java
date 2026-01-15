package com.example.makemytrip.booking.repository;

import com.example.makemytrip.Payment.Service.PaymentService;
import com.example.makemytrip.booking.model.Booking;
import com.example.makemytrip.booking.model.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
    boolean existsByBookingID(Integer bookingID);
    List<Booking> findByBookingID(Integer bookingID);

    boolean existsByUserid(Integer userid);

    List<Booking> findByUserid(Integer userid);

    @Transactional
    @Modifying
    @Query("update Booking b set b.status = BookingStatus.CANCELLED where b.bookingID = ?1")
    int updateStatusByBookingID(Integer bookingID);

    boolean existsByBookingIDAndStatus(Integer bookingID, BookingStatus status);

}
