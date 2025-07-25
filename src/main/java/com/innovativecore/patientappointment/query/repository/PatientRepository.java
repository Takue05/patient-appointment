package com.innovativecore.patientappointment.query.repository;

import com.innovativecore.patientappointment.query.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
    Patient findPatientByPatientId(String patientId);
}
