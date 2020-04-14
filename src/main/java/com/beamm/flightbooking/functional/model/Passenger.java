package com.beamm.flightbooking.functional.model;

import java.util.ArrayList;
import java.util.List;

public class Passenger {

    private Integer passengerID;
    private String passportNumber;
    private Person person;
    private List<Trip> trips = new ArrayList<>();

    public Passenger(Integer passengerID, String passportNumber, Person person, List<Trip> trips) {
        this.passengerID = passengerID;
        this.passportNumber = passportNumber;
        this.person = person;
        this.trips = trips;
    }

    public Integer getPassengerID() {
        return passengerID;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public Person getPerson() {
        return person;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public Trip addTrip(Trip trip) {

        boolean isAdded = trips.add(trip);

        return isAdded?trip:null;
    }

}
