package com.innovativecore.patientappointment.common.event;

public class AppointmentCancelledEvent extends BaseEvent<String> {
    private final String cancellationReason;

    public AppointmentCancelledEvent(String id ,String cancellationReason) {
        super(id);
        this.cancellationReason = cancellationReason;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }
}
