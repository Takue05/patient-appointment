package com.innovativecore.patientappointment.command.dto.appointmentDTO;

import com.innovativecore.patientappointment.command.aggregate.AppointmentStatus;
import lombok.Data;

@Data
public class CancelAppointmentRequest {
    String appointmentId;
    String cancellationReason;
    AppointmentStatus status;

}
