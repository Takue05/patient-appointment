package com.innovativecore.patientappointment.command.dto.patientDTO;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class RegisterPatientRequest {
    private UUID patientId;
    private  String firstName;
    private   String  lastName;
    private  LocalDate dateOfBirth;
    private   String gender;
    private   String  contactNumber;
    private   String  address;
    private Instant registeredAt;
}
