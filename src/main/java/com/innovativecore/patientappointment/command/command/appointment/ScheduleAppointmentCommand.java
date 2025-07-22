package com.innovativecore.patientappointment.command.command.appointment;


import com.innovativecore.patientappointment.command.aggregate.AppointmentStatus;
import lombok.NonNull;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;
@Value
public class ScheduleAppointmentCommand {
    @TargetAggregateIdentifier
    @NonNull
    String appointmentId;
    @NonNull
    String  patientId;
    String  doctorId;
    LocalDateTime appointmentDateTime;
    String reason;
    AppointmentStatus Status;

}
