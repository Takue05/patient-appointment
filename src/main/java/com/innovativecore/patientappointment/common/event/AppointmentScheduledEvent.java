package com.innovativecore.patientappointment.common.event;

import java.time.LocalDateTime;

public class AppointmentScheduledEvent extends BaseEvent<String>{
    private  final String  patientId;
    private  final String  doctorId;
    private final LocalDateTime appointmentDateTime;
    private  final String reason;

    public AppointmentScheduledEvent(String id , String patientId, String doctorId, LocalDateTime appointmentDateTime, String reason) {
        super(id);
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDateTime = appointmentDateTime;
        this.reason = reason;

    }

    public String getPatientId() {
        return patientId;
    }

    public String getReason() {
        return reason;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public String getDoctorId() {
        return doctorId;
    }
}
