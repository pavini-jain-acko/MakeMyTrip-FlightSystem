package com.example.makemytrip.search.repository;

import com.example.makemytrip.search.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
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


//    @Query("""
//            select f from Flight f
//            where (:source is NULL or f.source=:source)
//            and (:destination is NULL or f.destination=:destination)
//            and (:date is NULL or f.arrivalDate=:date)
//    """)
//
//    List<Flight> search(
//            @Param("source") String source,
//            @Param("destination") String destination,
//            @Param("date") LocalDate date
//            );
//@Query("""
//    select f from Flight f
//    where (:source is null or f.source = :source)
//      and (:destination is null or f.destination = :destination)
//      and (
//            :start is null or
//            f.arrivalDate >= :start and f.arrivalDate < :end
//          )
//""")
//List<Flight> search(
//        @Param("source") String source,
//        @Param("destination") String destination,
//        @Param("start") LocalDateTime start,
//        @Param("end") LocalDateTime end
//);

//    List<Flight> findBySourceAndDestinationAndDepartureDate(String source, String destination, LocalDateTime departureDate);
