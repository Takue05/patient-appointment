package com.innovativecore.patientappointment.command.dto.appointmentDTO;

import com.innovativecore.patientappointment.command.aggregate.AppointmentStatus;
import lombok.Value;

@Value
public class CompleteAppointmentRequest {
    String appointmentId;
    String  completeNotes;
    AppointmentStatus status;

}
