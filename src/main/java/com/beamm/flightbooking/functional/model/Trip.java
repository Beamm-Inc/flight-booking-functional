package com.beamm.flightbooking.functional.model;

public class Trip {

    private Integer tripID;
    private String seat;
    private Meal meal;
    private String ticketNumber;
    private FlightClass flightClass;
    private ScheduledFlight scheduledFlight;

    public Trip(Integer tripID, String seat, Meal meal, String ticketNumber, FlightClass flightClass, ScheduledFlight flight) {
        this.tripID = tripID;
        this.seat = seat;
        this.meal = meal;
        this.ticketNumber = ticketNumber;
        this.flightClass = flightClass;
        this.scheduledFlight = flight;
    }

    public Integer getTripID() {
        return tripID;
    }

    public String getSeat() {
        return seat;
    }

    public Meal getMeal() {
        return meal;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public FlightClass getFlightClass() {
        return flightClass;
    }

    public ScheduledFlight getScheduledFlight() {
        return scheduledFlight;
    }
}
