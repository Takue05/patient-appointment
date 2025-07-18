package com.innovativecore.patientappointment.command.service;

import com.innovativecore.patientappointment.command.aggregate.AppointmentStatus;
import com.innovativecore.patientappointment.command.command.appointment.CancelAppointmentCommand;
import com.innovativecore.patientappointment.command.command.appointment.CompleteAppointmentCommand;
import com.innovativecore.patientappointment.command.command.appointment.RescheduleAppointmentCommand;
import com.innovativecore.patientappointment.command.command.appointment.ScheduleAppointmentCommand;
import com.innovativecore.patientappointment.command.dto.appointmentDTO.CancelAppointmentRequest;
import com.innovativecore.patientappointment.command.dto.appointmentDTO.CompleteAppointmentRequest;
import com.innovativecore.patientappointment.command.dto.appointmentDTO.RescheduleAppointmentRequest;
import com.innovativecore.patientappointment.command.dto.appointmentDTO.ScheduleAppointmentRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AppointmentCommandService {
    private final CommandGateway commandGateway;

    public AppointmentCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<String> scheduleAppointment(ScheduleAppointmentRequest scheduleAppointmentRequest) {
        return commandGateway.send(new ScheduleAppointmentCommand(
                UUID.randomUUID().toString(),
                scheduleAppointmentRequest.getPatientId(),
                scheduleAppointmentRequest.getDoctorId(),
                scheduleAppointmentRequest.getAppointmentDateTime(),
                scheduleAppointmentRequest.getReason(),
                AppointmentStatus.SCHEDULED
        ));

    }

    public CompletableFuture<String> completeAppointment(CompleteAppointmentRequest completeApointmentRequest) {
        return commandGateway.send(new CompleteAppointmentCommand(
                completeApointmentRequest.getAppointmentId(),
                completeApointmentRequest.getCompleteNotes(),
                AppointmentStatus.COMPLETED
        ));
    }
    public CompletableFuture<String> cancelAppointment(CancelAppointmentRequest cancelAppointmentRequest) {
        return commandGateway.send(new CancelAppointmentCommand(
                cancelAppointmentRequest.getAppointmentId(),
                cancelAppointmentRequest.getCancellationReason(),
                AppointmentStatus.CANCELLED
        ));
    }

    public CompletableFuture<String> rescheduleAppointment(RescheduleAppointmentRequest rescheduleAppointmentRequest) {
        return commandGateway.send(new RescheduleAppointmentCommand(
                                rescheduleAppointmentRequest.getAppointmentId(),
                                rescheduleAppointmentRequest.getAppointmentDateTime(),
                                AppointmentStatus.RESCHEDULED
        ));
    }
}
