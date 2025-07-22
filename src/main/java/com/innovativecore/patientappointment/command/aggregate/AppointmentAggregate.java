package com.innovativecore.patientappointment.command.aggregate;

import com.innovativecore.patientappointment.command.command.appointment.CancelAppointmentCommand;
import com.innovativecore.patientappointment.command.command.appointment.CompleteAppointmentCommand;
import com.innovativecore.patientappointment.command.command.appointment.RescheduleAppointmentCommand;
import com.innovativecore.patientappointment.command.command.appointment.ScheduleAppointmentCommand;
import com.innovativecore.patientappointment.common.event.appointment.AppointmentCancelledEvent;
import com.innovativecore.patientappointment.common.event.appointment.AppointmentCompletedEvent;
import com.innovativecore.patientappointment.common.event.appointment.AppointmentRescheduledEvent;
import com.innovativecore.patientappointment.common.event.appointment.AppointmentScheduledEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Getter
@Aggregate
@Slf4j
public class AppointmentAggregate {

    // Getters
    @AggregateIdentifier
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private AppointmentStatus Status;

    public AppointmentAggregate() {
    }

    @CommandHandler
    public AppointmentAggregate(ScheduleAppointmentCommand scheduleAppointmentCommand) {
        log.debug("ScheduleAppointmentCommand received for ID: {}", scheduleAppointmentCommand.getAppointmentId());
//        if (scheduleAppointmentCommand.getAppointmentId() == null) {
//            throw new IllegalArgumentException("Appointment ID cannot be null or empty");
//        }
//        if (scheduleAppointmentCommand.getPatientId() == null) {
//            throw new IllegalArgumentException("Patient ID cannot be null or empty");
//        }
        apply(new AppointmentScheduledEvent(
                scheduleAppointmentCommand.getAppointmentId(),
                scheduleAppointmentCommand.getPatientId(),
                scheduleAppointmentCommand.getDoctorId(),
                scheduleAppointmentCommand.getAppointmentDateTime(),
                scheduleAppointmentCommand.getReason(),
                scheduleAppointmentCommand.getStatus()

        ));

    }
    @CommandHandler
    public void handle(CompleteAppointmentCommand completeAppointmentCommand) {
        log.debug("AppointmentCompletedEvent received for ID: {}", completeAppointmentCommand.getAppointmentId());
        if (completeAppointmentCommand.getAppointmentId() == null) {
            throw new IllegalArgumentException("Appointment ID cannot be null or empty");
        }
        if (completeAppointmentCommand.getCompleteNotes() == null) {
            throw new IllegalArgumentException("Complete Notes cannot be null or empty");
        }

        apply(new AppointmentCompletedEvent(
                completeAppointmentCommand.getAppointmentId(),
                completeAppointmentCommand.getCompleteNotes(),
                completeAppointmentCommand.getStatus()

        ));

    }

    @CommandHandler
    public void handle(RescheduleAppointmentCommand  rescheduleAppointmentCommand) {
        log.debug("RescheduleAppointmentCommand received for ID: {}", rescheduleAppointmentCommand.getAppointmentId());
        if (rescheduleAppointmentCommand.getAppointmentId() == null) {
            throw new IllegalArgumentException("Appointment ID cannot be null or empty");
        }
        if (rescheduleAppointmentCommand.getAppointmentDateTime() == null) {
            throw new IllegalArgumentException("The new appointment date cannot be null or empty");
        }

        apply(new AppointmentRescheduledEvent(
                rescheduleAppointmentCommand.getAppointmentId(),
                rescheduleAppointmentCommand.getAppointmentDateTime(),
                rescheduleAppointmentCommand.getStatus()
        ));
    }

    @CommandHandler
    public void handle(CancelAppointmentCommand cancelAppointmentCommand) {
        log.debug("CancelAppointmentCommand received for ID: {}", cancelAppointmentCommand.getAppointmentId());
        if (cancelAppointmentCommand.getAppointmentId() == null) {
            throw new IllegalArgumentException("Appointment ID cannot be null or empty");
        }
        if(cancelAppointmentCommand.getCancellationReason() == null) {
            throw new IllegalArgumentException("Cancellation Reason cannot be null or empty");
        }
        apply(new AppointmentCancelledEvent(
                cancelAppointmentCommand.getAppointmentId(),
                cancelAppointmentCommand.getCancellationReason(),
                cancelAppointmentCommand.getStatus()
        ));
    }

    @EventSourcingHandler
    public void handle(AppointmentScheduledEvent event) {
        this.appointmentId = event.getAppointmentId();
        this.patientId = event.getPatientId();
        this.doctorId = event.getDoctorId();
        this.Status = event.getStatus();

    }

}
