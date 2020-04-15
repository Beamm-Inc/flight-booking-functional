package com.beamm.flightbooking.functional.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Booking {

    private Integer bookingId;
    private Double price;
    private LocalDateTime dateTimeOfBooking;
    private String bookingReference;
    private String luggageAllownace;
    private List<Trip> trips = new ArrayList<>();
    private List<Passenger> passengers =new ArrayList<>();

    public Booking(Integer bookingId, Double price, LocalDateTime dateTimeOfBooking, String bookingReference, String luggageAllownace, List<Trip> trips, List<Passenger> passengers) {
        this.bookingId = bookingId;
        this.price = price;
        this.dateTimeOfBooking = dateTimeOfBooking;
        this.bookingReference = bookingReference;
        this.luggageAllownace = luggageAllownace;
        this.trips = trips;
        this.passengers = passengers;
        for (Passenger p : passengers){
            for (Trip t:trips) {
                t.getScheduledFlight().addPassenger(p);
            }
        }
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getDateTimeOfBooking() {
        return dateTimeOfBooking;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public String getLuggageAllownace() {
        return luggageAllownace;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public Passenger addPasseneger(Passenger passenger) {

        boolean isAdded = passengers.add(passenger);

        return isAdded?passenger:null;
    }

    public Trip addTrip(Trip trip) {

        boolean isAdded = trips.add(trip);

        return isAdded?trip:null;
    }

}
