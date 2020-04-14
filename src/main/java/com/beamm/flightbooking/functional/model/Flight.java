package com.beamm.flightbooking.functional.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalTime;

public class Flight
{
    private Integer flightID;
    private String flightNumber;
    private Airport origin;
    private Airport destination;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime departureTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime arrivalTime;

    private Double nominalPrice;
    private Double distance;

    public Flight(Integer flightID, String flightNumber, Airport origin, Airport destination, LocalTime departureTime, LocalTime arrivalTime, Double nominalPrice, Double distance) {
        this.flightID = flightID;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.nominalPrice = nominalPrice;
        this.distance = distance;
    }

    public Integer getFlightID() {
        return flightID;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public Airport getOrigin() {
        return origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public Double getNominalPrice() {
        return nominalPrice;
    }

    public Double getDistance() {
        return distance;
    }
}
