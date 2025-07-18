package com.innovativecore.patientappointment.query.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name="appointment")
public class Appointment {
    @Id
    String  appointmentId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", nullable = false, foreignKey = @ForeignKey(name = "fk_appointment_patient"))
    Patient  patient;
    String  doctorId;
    LocalDateTime appointmentDateTime;
    String appointmentReason;
    String Status;
    String notes;
    String appointCancellationReason;
}
