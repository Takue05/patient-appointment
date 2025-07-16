package com.innovativecore.patientappointment.command.dto;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

@Data
public class UpdatePatientRequest {
    private String Id;
    private  String contactNumber;
    private  String address;
    private  String gender;
    private Instant updatedAt;
    private  String updatedBy;
}
