package com.beamm.flightbooking.functional.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ScheduledFlight {

    private Integer scheduledflightID;
    private Flight flight;
    private Airplane airplane;
    private int capacity;
    private double currentPrice;
    private LocalDate departureDate;
    private LocalDate arrivalDate;

    private List<Passenger> passengers = new ArrayList<>(capacity);

    public ScheduledFlight(Flight flight, Airplane airplane, double currentPrice, LocalDate departureDate,
                           LocalDate arrivalDate)
    {
        this.flight = flight;
        this.airplane = airplane;
        this.capacity = airplane.getFirstClassSeats() + airplane.getAirplaneID() + airplane.getEconomyClassSeats();
        this.currentPrice = currentPrice;   //flight.getNominalPrice() + inflation;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;

        passengers = new ArrayList<>(this.capacity);
    }

    public ScheduledFlight(Integer scheduledflightID, Flight flight, Airplane airplane, int capacity, double currentPrice,
                           LocalDate departureDate, LocalDate arrivalDate, List<Passenger> passengers) {
        this.scheduledflightID = scheduledflightID;
        this.flight = flight;
        this.airplane = airplane;
        this.capacity = capacity;
        this.currentPrice = currentPrice;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.passengers = passengers;
    }

    public Integer getScheduledflightID() {
        return scheduledflightID;
    }

    public Flight getFlight() {
        return flight;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public Passenger addPassenger(Passenger passenger){
        boolean isAdded = passengers.add(passenger);
        return isAdded? passenger:null;
    }

    public List<Passenger> addPassenger(List<Passenger> passengers){
        boolean isAdded = passengers.addAll(passengers);
        return isAdded? passengers: null;
    }
}
