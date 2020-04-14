package com.beamm.flightbooking.functional.model;

public class Airport
{
    private Integer airportID;
    private String airportName;
    private String airportCode;
    private String airportCity;

    public Airport(Integer airportID, String airportName, String airportCode, String airportCity) {
        this.airportID = airportID;
        this.airportName = airportName;
        this.airportCode = airportCode;
        this.airportCity = airportCity;
    }

    public Integer getAirportID() {
        return airportID;
    }

    public String getAirportName() {
        return airportName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public String getAirportCity() {
        return airportCity;
    }
}
