package com.innovativecore.patientappointment.command.command;

public class CompleteAppointmentCommand extends BaseCommand <String> {
    private final String  completeNotes;

    public CompleteAppointmentCommand(String id, String completeNotes) {
        super(id);
        this.completeNotes = completeNotes;
    }
    public String getCompleteNotes() {
        return completeNotes;
    }

}