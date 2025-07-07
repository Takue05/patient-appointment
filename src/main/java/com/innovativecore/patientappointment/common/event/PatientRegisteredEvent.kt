package com.innovativecore.patientappointment.common.event

import java.time.Instant
import java.time.LocalDate
import java.util.UUID


data class PatientRegisteredEvent(
    val patientId: UUID,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate,
    val gender: String,
    val contactNumber: String,
    val address: String,
    val registrationTimestamp: Instant
)
