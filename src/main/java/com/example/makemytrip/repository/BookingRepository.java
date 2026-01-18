package com.example.makemytrip.repository;

import com.example.makemytrip.entity.Booking;
import com.example.makemytrip.enums.BookingStatus;
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
