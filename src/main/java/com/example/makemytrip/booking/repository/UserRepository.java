package com.example.makemytrip.booking.repository;

import com.example.makemytrip.booking.model.Booking;
import com.example.makemytrip.booking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByUserid(Integer userid);
}
