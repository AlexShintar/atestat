package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.Duration;
import java.util.List;
import java.util.function.Predicate;

public class GroundFilter {

    /**
     * @param onEarthHours number of hours on earth
     * Filtering flights with more than a specified number of hours between them
     */
    public static Predicate<Flight> isLongerThan(int onEarthHours) {
        return f -> {
            List<Segment> segments = f.getSegments();
            Duration totalTime = Duration.ZERO;
            Duration segmentTime;

            for (int i = 1; i < segments.size(); i++) {

                segmentTime = Duration.between(segments.get(i - 1).getArrivalDate(), segments.get(i).getDepartureDate());

                if (!segmentTime.isNegative()) {
                    totalTime = totalTime.plus(segmentTime);
                }
            }

            return totalTime.toHours() <= onEarthHours;
        };
    }
}
