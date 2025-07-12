package com.innovativecore.patientappointment.command.command;


import java.time.LocalDateTime;

public class RescheduleAppointmentCommand extends BaseCommand <String>{
    private final LocalDateTime newAppointmentDateTime;

    public RescheduleAppointmentCommand(String id ,LocalDateTime newAppointmentDateTime) {
        super(id);
        this.newAppointmentDateTime = newAppointmentDateTime;
    }
    public LocalDateTime getNewAppointmentDateTime() {
        return newAppointmentDateTime;
    }
}
