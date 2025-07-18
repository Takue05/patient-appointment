package com.innovativecore.patientappointment.common.event.appointment;

import com.innovativecore.patientappointment.command.aggregate.AppointmentStatus;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class AppointmentScheduledEvent{
    String  appointmentId;
    String  patientId;
    String  doctorId;
    LocalDateTime appointmentDateTime;
    String reason;
    AppointmentStatus Status;



}
