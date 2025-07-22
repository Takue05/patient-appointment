package com.innovativecore.patientappointment.query.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Table(catalog = "Appointments", schema="Appointments" )
public class Patient {
    @Id
    private  String  patientId;
    private  String  firstName;
    private String  lastName;
    private  LocalDate dateOfBirth;
    private   String gender;
    private   String  contactNumber;
    private  String  address;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments = new ArrayList<>();
    private Instant registeredAt;
}



