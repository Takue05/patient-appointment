package com.innovativecore.patientappointment.query;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID patientId;
    private  String firstName;
    private  String lastName;
    private String dateOfBirth;
    private String gender;
    private String contactNumber;
    private String address;

}
