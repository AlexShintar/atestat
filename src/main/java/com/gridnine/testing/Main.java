package com.gridnine.testing;

import com.gridnine.testing.filter.ArrivalFilter;
import com.gridnine.testing.filter.DepartureFilter;
import com.gridnine.testing.filter.GroundFilter;
import com.gridnine.testing.model.Flight;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> filter;

        System.out.println("All flights:");
        printer(flights);

        System.out.println("Departure time is later than current time:");
        filter = flights.stream().filter(DepartureFilter.isBeforeNow()).toList();
        printer(filter);

        System.out.println("Arrival time is not earlier than departure time:");
        filter = flights.stream().filter(ArrivalFilter.isBeforeDeparture()).toList();
        printer(filter);

        System.out.println("2 or less hours on the ground between flights:");
        filter = flights.stream().filter(GroundFilter.isLongerThan(2)).toList();
        printer(filter);

        System.out.println("All previous conditions:");
        filter = flights.stream()
                .filter(DepartureFilter.isBeforeNow())
                .filter(ArrivalFilter.isBeforeDeparture())
                .filter(GroundFilter.isLongerThan(2))
                .toList();
        printer(filter);
    }

    private static void printer(List<Flight> flights) {
        for (Flight f : flights) {
            System.out.println(f);
        }
        System.out.println();
    }
}
