package com.innovativecore.patientappointment.command.dto.appointmentDTO;
import com.innovativecore.patientappointment.command.aggregate.AppointmentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RescheduleAppointmentRequest {
    String appointmentId;
    LocalDateTime AppointmentDateTime;
    AppointmentStatus status;

}
