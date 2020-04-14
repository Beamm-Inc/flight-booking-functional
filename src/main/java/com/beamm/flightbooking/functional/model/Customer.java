package com.beamm.flightbooking.functional.model;

public class Customer {
    private Integer customerID;
    private Person person;

    public Customer(Integer customerID, Person person) {
        this.customerID = customerID;
        this.person = person;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public Person getPerson() {
        return person;
    }
}
