package com.example.makemytrip.service;

import com.example.makemytrip.entity.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface SearchService{
//    List<Flight> getAllFlights(String origin,String destination, Date date);
    List<Flight> search(String source,String destination, LocalDateTime date) throws Exception;
//    void saveFlight(Flight );
}
