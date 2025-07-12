package com.innovativecore.patientappointment.command.aggregate;

import com.innovativecore.patientappointment.command.command.CancelAppointmentCommand;
import com.innovativecore.patientappointment.command.command.CompleteAppointmentCommand;
import com.innovativecore.patientappointment.command.command.ScheduleAppointmentCommand;
import com.innovativecore.patientappointment.common.event.AppointmentCancelledEvent;
import com.innovativecore.patientappointment.common.event.AppointmentCompletedEvent;
import com.innovativecore.patientappointment.common.event.AppointmentScheduledEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDateTime;

@Aggregate
@Slf4j
public class AppointmentAggregate {

    @AggregateIdentifier
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private LocalDateTime appointmentDateTime;
    private String reason;
    private String cancellationReason;
    private String completeNotes;
    private String status;

    public AppointmentAggregate() {
    }

    @CommandHandler
    public AppointmentAggregate(ScheduleAppointmentCommand scheduleAppointmentCommand) {
        log.debug("ScheduleAppointmentCommand received for ID: {}", scheduleAppointmentCommand.getId());

        if (scheduleAppointmentCommand.getId() == null || scheduleAppointmentCommand.getId().isEmpty()) {
            throw new IllegalArgumentException("Appointment ID cannot be null or empty");
        }

        if (scheduleAppointmentCommand.getAppointmentDateTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Cannot schedule appointment in the past");
        }

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
        log.debug("AppointmentScheduledEvent occurred for ID: {}", appointmentScheduledEvent.getId());
        this.appointmentId = appointmentScheduledEvent.getId();
        this.patientId = appointmentScheduledEvent.getPatientId();
        this.doctorId = appointmentScheduledEvent.getDoctorId();
        this.appointmentDateTime = appointmentScheduledEvent.getAppointmentDateTime();
        this.reason = appointmentScheduledEvent.getReason();
        this.status = "SCHEDULED";
    }

    @CommandHandler
    public void handle(CancelAppointmentCommand cancelAppointmentCommand) {
        log.debug("CancelAppointmentCommand received for ID: {}", cancelAppointmentCommand.getId());

        if ("CANCELLED".equals(this.status)) {
            throw new IllegalStateException("Appointment is already cancelled");
        }

        if ("COMPLETED".equals(this.status)) {
            throw new IllegalStateException("Cannot cancel a completed appointment");
        }

        AggregateLifecycle.apply(new AppointmentCancelledEvent(
                cancelAppointmentCommand.getId(),
                cancelAppointmentCommand.getCancellationReason()
        ));
    }

    @EventSourcingHandler
    public void on(AppointmentCancelledEvent appointmentCancelledEvent) {
        log.debug("AppointmentCancelledEvent occurred for ID: {}", appointmentCancelledEvent.getId());
        this.cancellationReason = appointmentCancelledEvent.getCancellationReason();
        this.status = "CANCELLED";
    }

    @CommandHandler
    public void handle(CompleteAppointmentCommand completeAppointmentCommand) {
        log.debug("CompleteAppointmentCommand received for ID: {}", completeAppointmentCommand.getId());

        if ("CANCELLED".equals(this.status)) {
            throw new IllegalStateException("Cannot complete a cancelled appointment");
        }

        if ("COMPLETED".equals(this.status)) {
            throw new IllegalStateException("Appointment is already completed");
        }

        AggregateLifecycle.apply(new AppointmentCompletedEvent(
                completeAppointmentCommand.getId(),
                completeAppointmentCommand.getCompleteNotes()
        ));
    }

    @EventSourcingHandler
    public void on(AppointmentCompletedEvent appointmentCompletedEvent) {
        log.debug("AppointmentCompletedEvent occurred for ID: {}", appointmentCompletedEvent.getId());
        this.completeNotes = appointmentCompletedEvent.getCompleteNotes();
        this.status = "COMPLETED";
    }

    // Getters
    public String getAppointmentId() { return appointmentId; }
    public String getPatientId() { return patientId; }
    public String getDoctorId() { return doctorId; }
    public LocalDateTime getAppointmentDateTime() { return appointmentDateTime; }
    public String getReason() { return reason; }
    public String getCancellationReason() { return cancellationReason; }
    public String getCompleteNotes() { return completeNotes; }
    public String getStatus() { return status; }
}