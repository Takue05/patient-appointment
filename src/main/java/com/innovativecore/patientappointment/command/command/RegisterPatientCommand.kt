package com.innovativecore.patientappointment.command.command

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.LocalDate
import java.util.UUID

data class RegisterPatientCommand(
    @TargetAggregateIdentifier
    val patientId: UUID,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate,
    val gender: String,
    val contactNumber: String,
    val address: String
)