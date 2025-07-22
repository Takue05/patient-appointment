package com.innovativecore.patientappointment.command.command.patient;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class RegisterPatientCommand{
    @TargetAggregateIdentifier
    String patientId;
    String  firstName;
    String  lastName;
    LocalDate dateOfBirth;
    String gender;
    String  contactNumber;
    String  address;
    Instant  registeredAt;


    public RegisterPatientCommand(String patientId, String firstName, String lastName, LocalDate dateOfBirth, String gender, String contactNumber, String address, Instant registeredAt) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.address = address;
        this.registeredAt = registeredAt;

    }
}
