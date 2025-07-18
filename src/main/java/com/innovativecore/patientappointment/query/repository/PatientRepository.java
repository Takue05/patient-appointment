package com.innovativecore.patientappointment.query.repository;

import com.innovativecore.patientappointment.query.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
}
