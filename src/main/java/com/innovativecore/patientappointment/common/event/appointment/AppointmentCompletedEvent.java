package com.innovativecore.patientappointment.common.event.appointment;
import ch.qos.logback.core.status.Status;
import com.innovativecore.patientappointment.command.aggregate.AppointmentStatus;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Value
public class AppointmentCompletedEvent {
    String appointmentId;
    String  completeNotes;
    AppointmentStatus status;


}
