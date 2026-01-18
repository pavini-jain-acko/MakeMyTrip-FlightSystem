package com.example.makemytrip.service.impl;

import com.example.makemytrip.entity.Flight;
import com.example.makemytrip.repository.FlightRepository;
import com.example.makemytrip.service.SearchService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class SearchServiceImpl implements SearchService {
    private final FlightRepository flightRepository;

    public SearchServiceImpl(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }

//    public List<Flight> search(String source, String destination, LocalDate date) {
//        if (source == null && destination == null && date !=null)
//            return flightRepository.findAll();
//        return flightRepository.search(source,destination,date);
//    }
@Override
public List<Flight> search(String source, String destination, LocalDateTime date) throws Exception{

    if (date == null && source == null && destination == null) {
        return flightRepository.findAll();
    } else if (date == null || source == null || destination == null) {
        throw new ResponseStatusException(BAD_REQUEST, "Invalid Search Parameters");
    }
    LocalDateTime startOfDate = date.toLocalDate().atStartOfDay();
    LocalDateTime endOfDate = date.toLocalDate().atTime(23,59,59);

    return flightRepository.findBySourceAndDestinationAndDepartureDateBetween(source, destination, startOfDate, endOfDate);

}


//    public List<Flight> search(String origin, String destination, Date date){
//        return flightRepository.search(origin, destination, date);
//    }

//    @Override
//    public void saveFlight(Flight flight) {
//        flightRepository.save(flight);
//    }
}
