package com.innovativecore.patientappointment.command.aggregate;
import com.innovativecore.patientappointment.command.command.CancelAppointmentCommand;
import com.innovativecore.patientappointment.command.command.CompleteAppointmentCommand;
import com.innovativecore.patientappointment.command.command.RegisterPatientCommand;
import com.innovativecore.patientappointment.command.command.ScheduleAppointmentCommand;
import com.innovativecore.patientappointment.common.event.AppointmentCancelledEvent;
import com.innovativecore.patientappointment.common.event.AppointmentCompletedEvent;
import com.innovativecore.patientappointment.common.event.AppointmentScheduledEvent;
import com.innovativecore.patientappointment.common.event.PatientRegisteredEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Aggregate
@Slf4j
public class AppointmentAggregate {
    @AggregateIdentifier
     private String appointmentId;
     private String patientId;
     private String doctorId;
     private String firstName;
     private String lastName;
    private LocalDate dateOfBirth;
    private  String gender;
    private  String  contactNumber;
    private   String  address;
    private  LocalDateTime appointmentDateTime;
    private  String reason;
    private  String cancellationReason;
    private String completeNotes;

    public AppointmentAggregate(){
    }

    @CommandHandler
    public AppointmentAggregate(RegisterPatientCommand registerPatientCommand) {
        log.debug("RegisterPatientCommand received.");
        AggregateLifecycle.apply(new PatientRegisteredEvent(
                registerPatientCommand.getId(),
                registerPatientCommand.getFirstName(),
                registerPatientCommand.getLastName(),
                registerPatientCommand.getDateOfBirth(),
                registerPatientCommand.getAddress(),
                registerPatientCommand.getGender(),
                registerPatientCommand.getContactNumber()
        ));
    }

    @EventSourcingHandler
    public void on(PatientRegisteredEvent patientRegisteredEvent) {
        log.debug("PatientRegisteredEvent occured.");
        this.patientId = patientRegisteredEvent.getId();
        this.firstName = patientRegisteredEvent.getFirstName();
        this.lastName = patientRegisteredEvent.getLastName();
        this.dateOfBirth = patientRegisteredEvent.getDateOfBirth();
        this.gender = patientRegisteredEvent.getGender();
        this.contactNumber = patientRegisteredEvent.getContactNumber();
        this.address = patientRegisteredEvent.getAddress();
    }

    @CommandHandler
    public void on(ScheduleAppointmentCommand scheduleAppointmentCommand) {
        log.debug("ScheduleAppointmentCommand received.");
        AggregateLifecycle.apply(new AppointmentScheduledEvent(
                scheduleAppointmentCommand.getId(),
                scheduleAppointmentCommand.getPatientId(),
                scheduleAppointmentCommand.getDoctorId(),
                scheduleAppointmentCommand.getAppointmentDateTime(),
                scheduleAppointmentCommand.getReason()

        ));
    }


    @EventSourcingHandler
    public void on(AppointmentScheduledEvent appointmentScheduledEvent) {
        log.debug("AppointmentScheduledEvent occured.");
        this.appointmentId = appointmentScheduledEvent.getId();
        this.patientId = appointmentScheduledEvent.getPatientId();
        this.doctorId = appointmentScheduledEvent.getDoctorId();
        this.appointmentDateTime = appointmentScheduledEvent.getAppointmentDateTime();
        this.reason = appointmentScheduledEvent.getReason();
    }

    @CommandHandler
    public void on(CancelAppointmentCommand cancelAppointmentCommand) {
        log.debug("CancelAppointmentCommand received.");
        AggregateLifecycle.apply(new AppointmentCancelledEvent(
                cancelAppointmentCommand.getId(),
                cancelAppointmentCommand.getCancellationReason()
        ));

    }

    @CommandHandler
    public void on(CompleteAppointmentCommand completeAppointmentCommand) {
        log.debug("CompleteAppointmentCommand received.");
        AggregateLifecycle.apply(new AppointmentCompletedEvent(
                completeAppointmentCommand.getId(),
                completeAppointmentCommand.getCompleteNotes()
        ));
    }

    @EventSourcingHandler
    public void on(AppointmentCompletedEvent appointmentCompletedEvent) {
        log.debug("AppointmentCompletedEvent occured.");
        this.appointmentId = appointmentCompletedEvent.getId();
        this.completeNotes = appointmentCompletedEvent.getCompleteNotes();
    }

    @EventSourcingHandler
    public void on(AppointmentCancelledEvent appointmentCancelledEvent) {
        log.debug("AppointmentCancelledEvent occured.");
        this.appointmentId = appointmentCancelledEvent.getId();
        this.cancellationReason = appointmentCancelledEvent.getCancellationReason();
    }


}
