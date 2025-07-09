package com.innovativecore.patientappointment.command.command;


import java.time.LocalDateTime;

public class ScheduleAppointmentCommand  extends BaseCommand<String> {
    private  final String  patientId;
    private  final String  doctorId;
    private final LocalDateTime appointmentDateTime;
    private  final String reason;

    public ScheduleAppointmentCommand(String id, String patientId, String doctorId, LocalDateTime appointmentDateTime, String reason) {
        super(id);
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDateTime = appointmentDateTime;
        this.reason = reason;
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

    public String getPatientId() {
        return patientId;
    }


}
