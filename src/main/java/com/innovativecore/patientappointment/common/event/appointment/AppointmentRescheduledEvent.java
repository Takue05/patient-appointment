package com.innovativecore.patientappointment.common.event.appointment;

import com.innovativecore.patientappointment.command.aggregate.AppointmentStatus;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

@Value
public class AppointmentRescheduledEvent {
    String appointmentId;
    LocalDateTime AppointmentDateTime;
    AppointmentStatus status;

}