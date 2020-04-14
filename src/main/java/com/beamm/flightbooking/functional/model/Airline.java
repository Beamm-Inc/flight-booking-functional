package com.beamm.flightbooking.functional.model;

import java.util.List;

public class Airline {
    private Integer airlineID;
    private String airlineName;
    private List<ScheduledFlight> scheduledFlights;
    private List<Booking> bookings;

    public Airline(Integer airlineID, String airlineName, List<ScheduledFlight> scheduledFlights, List<Booking> bookings) {
        this.airlineID = airlineID;
        this.airlineName = airlineName;
        this.scheduledFlights = scheduledFlights;
        this.bookings = bookings;
    }

    public Integer getAirlineID() {
        return airlineID;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public List<ScheduledFlight> getScheduledFlights() {
        return scheduledFlights;
    }

    public List<Booking> getBookings() {
        return bookings;
    }
}
