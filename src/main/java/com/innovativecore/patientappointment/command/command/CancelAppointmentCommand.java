package com.innovativecore.patientappointment.command.command;


public class CancelAppointmentCommand extends BaseCommand<String>{
    private final String cancellationReason;

    public CancelAppointmentCommand(String id ,String cancellationReason) {
        super(id);
        this.cancellationReason = cancellationReason;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }
}
