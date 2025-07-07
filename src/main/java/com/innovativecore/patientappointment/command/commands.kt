package com.innovativecore.patientappointment.command

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class RegisterPatientCommand(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate,
    val gender: String,
    val contactNumber: String,
    val address: String


)


data class ScheduleAppointmentCommand(
    @TargetAggregateIdentifier
    val patientId: UUID,
    val doctorId: String,
    val appointmentDateTime: LocalDateTime,
    val reason: String

)


data class RescheduleAppointmentCommand(
    @TargetAggregateIdentifier
    val appointmentId: UUID,
    val newAppointmentDateTime: LocalDateTime,

)

data class CancelAppointmentCommand(
    @TargetAggregateIdentifier
    val appointmentId: UUID,
    val cancellationReason: String,

)

data class  CompleteAppointmentCommand(
    @TargetAggregateIdentifier
    val appointmentId: UUID,
    val completeNotes: String,


)

data class AddMedicalNotesCommand(
    @TargetAggregateIdentifier
    val patientId: UUID,
    val doctorId: String,
    val notes: String,
    val visitDate: LocalDate


)