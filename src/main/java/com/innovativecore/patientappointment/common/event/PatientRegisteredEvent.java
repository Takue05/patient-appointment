package com.innovativecore.patientappointment.common.event;

import java.time.Instant;
import java.time.LocalDate;

public class PatientRegisteredEvent extends BaseEvent<String>{
    private  final String  firstName;
    private  final String  lastName;
    private final LocalDate dateOfBirth;
    private  final String gender;
    private  final String  contactNumber;
    private  final String  address;
    private final Instant registeredAt;

    public PatientRegisteredEvent(String id , String firstName, String lastName, LocalDate dateOfBirth, String gender, String contactNumber, String address, Instant registeredAt) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.address = address;
        this.registeredAt = registeredAt;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getAddress() {
        return address;
    }
    public Instant getRegisteredAt() {
        return registeredAt;
    }
}
