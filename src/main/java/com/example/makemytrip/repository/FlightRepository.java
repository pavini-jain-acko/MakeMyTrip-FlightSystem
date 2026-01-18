package com.example.makemytrip.repository;

import com.example.makemytrip.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Integer>{
    ArrayList<Flight> findAll();
    List<Flight> findBySourceAndDestinationAndDepartureDateBetween(String source, String destination, LocalDateTime departureDateStart, LocalDateTime departureDateEnd);

    boolean existsByFlightidAndSeatsavailableGreaterThan(Integer flightid, Integer seatsavailable);

    @Transactional
    @Modifying
    @Query("UPDATE Flight f SET f.seatsavailable = f.seatsavailable - 1 WHERE f.flightid = :flightid AND f.seatsavailable > 0")
    int updateSeatsavailableByFlightid(Integer flightid);

    @Transactional
    @Modifying
    @Query("update Flight f set f.seatsavailable = f.seatsavailable + 1 where f.flightid = :flightid")
    int increaseSeatAvailable(Integer flightid);


}
