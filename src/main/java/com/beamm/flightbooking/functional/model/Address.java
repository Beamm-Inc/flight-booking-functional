package com.beamm.flightbooking.functional.model;

public class Address {

    private Integer addressID;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zip;

    public Address() {}

    public Address(Integer addressID, String street, String city, String state, String country, String zip) {
        this.addressID = addressID;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
    }

    public Integer getAddressID() {
        return addressID;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZip() {
        return zip;
    }
}
