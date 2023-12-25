package com.gridnine.testing.filter;
import com.gridnine.testing.model.Flight;
import java.time.LocalDateTime;

import java.util.function.Predicate;

public class DepartureFilter {

    /**
     * Filtering flights whose departure time is earlier than the current time
     */
    public static Predicate<Flight> isBeforeNow() {
        return f -> f.getSegments().stream().anyMatch(s -> !s.getDepartureDate().isBefore(LocalDateTime.now()));
    }
}
