package com.beamm.flightbooking.functional.model;

import java.time.LocalDate;

public class Person {

    private Integer personID;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthDate;
    private String userName;
    private String gender;
    private String phoneNumber;
    private String email;
    private Address address = new Address();

    public Person(Integer personID, String firstName, String middleName, String lastName, LocalDate birthDate, String userName, String gender, String phoneNumber, String email, Address address) {
        this.personID = personID;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.userName = userName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public Integer getPersonID() {
        return personID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getUserName() {
        return userName;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }
}