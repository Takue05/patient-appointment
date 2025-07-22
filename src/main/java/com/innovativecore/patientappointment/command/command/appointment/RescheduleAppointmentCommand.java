package com.innovativecore.patientappointment.command.command.appointment;

import com.innovativecore.patientappointment.command.aggregate.AppointmentStatus;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;
@Value
public class RescheduleAppointmentCommand {
    @TargetAggregateIdentifier
    String appointmentId;
    LocalDateTime AppointmentDateTime;
    AppointmentStatus status;
}
