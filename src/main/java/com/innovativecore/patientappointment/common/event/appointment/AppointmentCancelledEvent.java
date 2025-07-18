package com.innovativecore.patientappointment.common.event.appointment;

import com.innovativecore.patientappointment.command.aggregate.AppointmentStatus;
import lombok.Value;

@Value
public class AppointmentCancelledEvent{
    String appointmentId;
    String cancellationReason;
    AppointmentStatus status;

    }

