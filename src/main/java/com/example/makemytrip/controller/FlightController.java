package com.example.makemytrip.controller;

import com.example.makemytrip.entity.Flight;
import com.example.makemytrip.service.SearchService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

//GET /flights : Get all flights
//GET /flights?origin={origin}&destination={destination}&date={date} : Search flights based on criteria (can have arrival date and one way, round trip option)

@RestController
public class FlightController {
    private final SearchService searchService;

    public FlightController(SearchService searchService){
        this.searchService = searchService;
    }

//    @GetMapping("/flights")
//    public List<Flight> getAllFlights(){
//        return searchService.getAllFlights();
//    }

//    @PostMapping("/add-flight")
//    public void addFlight(@RequestBody Flight flight) {
//        searchService.saveFlight(flight);
//    }

    @GetMapping("/flight")
    public List<Flight> search(
        @RequestParam(required = false) String source,
        @RequestParam(required = false) String destination,
        @RequestParam(required = false)  LocalDateTime date
    ) throws Exception{
        return searchService.search(source, destination, date);
    }
}
