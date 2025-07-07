package com.innovativecore.patientappointment.command

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class PatientRegisteredEvent(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate,
    val gender: String,
    val contactNumber: String,
    val address: String

)


data class AppointmentScheduledEvent(
    val patientId: UUID,
    val doctorId: String,
    val appointmentDateTime: LocalDateTime,
    val reason: String
)


data class AppointmentRescheduledEvent(
    val appointmentId: UUID,
    val newAppointmentDateTime: LocalDateTime,
    )

data class AppointmentCancelledEvent(
    val appointmentId: UUID,
    val cancellationReason: String,
    )

data class  AppointmentCompletedEvent(
    val appointmentId: UUID,
    val completeNotes: String,

    )

data class MedicalNotesAddedEvent(
    val patientId: UUID,
    val doctorId: String,
    val notes: String,
    val visitDate: LocalDate,


)