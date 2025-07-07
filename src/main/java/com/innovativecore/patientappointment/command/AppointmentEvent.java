package com.innovativecore.patientappointment.command;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDate;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class AppointmentEvent {
    private UUID patientId;
    private String doctorId;
    private LocalDate appointmentDateTime;
    private String reason;


    public AppointmentEvent() {
    }

    @CommandHandler
    public AppointmentEvent(ScheduleAppointmentCommand command) {
        apply(new ScheduleAppointmentCommand(
                 command.getPatientId(),
                command.getDoctorId(),
                 command.getAppointmentDateTime(),
                 command.getReason()
                )
        );
    }

    @CommandHandler
    public AppointmentEvent(RescheduleAppointmentCommand command) {
        apply(new RescheduleAppointmentCommand(
                command.getAppointmentId(),
                command.getNewAppointmentDateTime()
            )
        );
    }

    @CommandHandler
    public AppointmentEvent(CancelAppointmentCommand command) {
        apply(new CancelAppointmentCommand(
                command.getAppointmentId(),
                command.getCancellationReason()
            )
        );
    }

    @CommandHandler
    public AppointmentEvent(CompleteAppointmentCommand command) {
        apply(new CompleteAppointmentCommand(
                command.getAppointmentId(),
                command.getCompleteNotes()
            )
        );
    }
}
