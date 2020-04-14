package com.beamm.flightbooking.functional.model;

public class Airplane
{
    private Integer airplaneID;
    private String airplaneSerialNumber;
    private String airplaneModel;
    private int firstClassSeats;
    private int businessClassSeats;
    private int economyClassSeats;

    public Airplane(Integer airplaneID, String airplaneSerialNumber, String airplaneModel, int firstClassSeats, int businessClassSeats, int economyClassSeats) {
        this.airplaneID = airplaneID;
        this.airplaneSerialNumber = airplaneSerialNumber;
        this.airplaneModel = airplaneModel;
        this.firstClassSeats = firstClassSeats;
        this.businessClassSeats = businessClassSeats;
        this.economyClassSeats = economyClassSeats;
    }

    public Integer getAirplaneID() {
        return airplaneID;
    }

    public String getAirplaneSerialNumber() {
        return airplaneSerialNumber;
    }

    public String getAirplaneModel() {
        return airplaneModel;
    }

    public int getFirstClassSeats() {
        return firstClassSeats;
    }

    public int getBusinessClassSeats() {
        return businessClassSeats;
    }

    public int getEconomyClassSeats() {
        return economyClassSeats;
    }
}
