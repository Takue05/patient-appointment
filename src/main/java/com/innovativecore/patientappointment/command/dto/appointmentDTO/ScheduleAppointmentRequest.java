package com.innovativecore.patientappointment.command.dto.appointmentDTO;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ScheduleAppointmentRequest {
    String  appointmentId;
    String  patientId;
    String  doctorId;
    LocalDateTime appointmentDateTime;
    String reason;
}
