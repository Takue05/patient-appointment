package com.innovativecore.patientappointment.command.command.appointment;

import com.innovativecore.patientappointment.command.aggregate.AppointmentStatus;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CancelAppointmentCommand {
    @TargetAggregateIdentifier
    String appointmentId;
   String cancellationReason;
   AppointmentStatus status;

}
