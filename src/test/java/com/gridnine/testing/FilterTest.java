package com.gridnine.testing;

import com.gridnine.testing.filter.ArrivalFilter;
import com.gridnine.testing.filter.DepartureFilter;
import com.gridnine.testing.filter.GroundFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FilterTest {
    private List<Flight> flights = new ArrayList<>();
    private List<Flight> filter;

    @BeforeEach
    void init() {
        flights = FlightBuilder.createFlights();
    }

    @Test
    void departureFilterTest() {
        filter = flights.stream().filter(DepartureFilter.isBeforeNow()).toList();
        assertEquals(5, filter.size());
        for (Flight flight : filter) {
            for (Segment segment : flight.getSegments()) {
                assertTrue(segment.getDepartureDate().isAfter(LocalDateTime.now()));
            }
        }
    }

    @Test
    void arrivalFilterTest() {
        filter = flights.stream().filter(ArrivalFilter.isBeforeDeparture()).toList();
        assertEquals(5, filter.size());
        for (Flight flight : filter) {
            for (Segment segment : flight.getSegments()) {
                assertTrue(segment.getArrivalDate().isAfter(segment.getDepartureDate()));
            }
        }
    }

    @Test
    void groundFilterTest() {
        filter = flights.stream().filter(GroundFilter.isLongerThan(3)).toList();
        assertEquals(6, filter.size());
        filter = flights.stream().filter(GroundFilter.isLongerThan(2)).toList();
        assertEquals(4, filter.size());
        filter = flights.stream().filter(GroundFilter.isLongerThan(1)).toList();
        assertEquals(4, filter.size());
        filter = flights.stream().filter(GroundFilter.isLongerThan(0)).toList();
        assertEquals(3, filter.size());
    }

    @Test
    void allFiltersTest() {
        filter = flights.stream()
                .filter(DepartureFilter.isBeforeNow())
                .filter(ArrivalFilter.isBeforeDeparture())
                .filter(GroundFilter.isLongerThan(2))
                .toList();
        assertEquals(2, filter.size());
    }
}