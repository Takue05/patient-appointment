package com.innovativecore.patientappointment.query.repository;

import com.innovativecore.patientappointment.query.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    Appointment findAppointmentByAppointmentId(String appointmentId);
}
