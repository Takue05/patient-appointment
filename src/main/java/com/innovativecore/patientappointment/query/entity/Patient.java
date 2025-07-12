package com.innovativecore.patientappointment.query.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
public class Patient {
    @Id
    private  String  patientId;
    private  String  firstName;
    private String  lastName;
    private  LocalDate dateOfBirth;
    private   String gender;
    private   String  contactNumber;
    private  String  address;
    private Instant registeredAt;
}
