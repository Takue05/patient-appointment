package com.innovativecore.patientappointment.common.event;

import java.time.LocalDateTime;

public class AppointmentRescheduledEvent extends BaseEvent<String>{

    private final LocalDateTime newAppointmentDateTime;

    public AppointmentRescheduledEvent(String id ,LocalDateTime newAppointmentDateTime) {
        super(id);
        this.newAppointmentDateTime = newAppointmentDateTime;
    }
    public LocalDateTime getNewAppointmentDateTime() {
        return newAppointmentDateTime;
    }
}
