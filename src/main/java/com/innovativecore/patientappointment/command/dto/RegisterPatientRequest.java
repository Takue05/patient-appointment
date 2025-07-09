package com.innovativecore.patientappointment.command.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class RegisterPatientRequest {
    private  String  firstName;
    private   String  lastName;
    private  LocalDate dateOfBirth;
    private   String gender;
    private   String  contactNumber;
    private   String  address;
}
