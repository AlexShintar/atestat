package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.util.function.Predicate;

public class ArrivalFilter {

    /**
     * Filtering flights whose arrival time is earlier than departure time
     */
    public static Predicate<Flight> isBeforeDeparture() {
        return f -> f.getSegments().stream().anyMatch(s -> !s.getArrivalDate().isBefore(s.getDepartureDate()));
    }

    /**
     * Filtering flights whose arrival day is earlier than departure day
     */
    public static Predicate<Flight> isDayBeforeDeparture() {
        return f -> f.getSegments().stream().anyMatch(s -> !s.getArrivalDate().toLocalDate()
                .isBefore(s.getDepartureDate().toLocalDate()));
    }
}
