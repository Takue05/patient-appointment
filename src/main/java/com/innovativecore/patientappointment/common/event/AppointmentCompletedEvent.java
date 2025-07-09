package com.innovativecore.patientappointment.common.event;

public class AppointmentCompletedEvent extends BaseEvent<String> {

    private final String  completeNotes;

    public AppointmentCompletedEvent(String id, String completeNotes) {
        super(id);
        this.completeNotes = completeNotes;
    }
    public String getCompleteNotes() {
        return completeNotes;
    }


}
