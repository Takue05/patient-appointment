package com.innovativecore.patientappointment.common.event.patient;

import lombok.Value;

import java.time.Instant;
import java.time.LocalDate;

@Value
public class PatientRegisteredEvent{
    String patientId;
    String  firstName;
    String  lastName;
    LocalDate dateOfBirth;
    String gender;
    String  contactNumber;
    String  address;
    Instant registeredAt;


    }


