package com.innovativecore.patientappointment.query.service;

import com.innovativecore.patientappointment.common.event.appointment.AppointmentCancelledEvent;
import com.innovativecore.patientappointment.common.event.appointment.AppointmentCompletedEvent;
import com.innovativecore.patientappointment.common.event.appointment.AppointmentRescheduledEvent;
import com.innovativecore.patientappointment.common.event.appointment.AppointmentScheduledEvent;
import com.innovativecore.patientappointment.common.event.patient.PatientUpdatedEvent;
import com.innovativecore.patientappointment.query.entity.Appointment;
import com.innovativecore.patientappointment.query.entity.Patient;
import com.innovativecore.patientappointment.query.repository.AppointmentRepository;
import com.innovativecore.patientappointment.query.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ManageApointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    public ManageApointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
    }

    @EventHandler
    public void on(AppointmentScheduledEvent appointmentScheduledEvent){
        log.info("Handling Patient Scheduled Event...");
        Appointment  appointment = new Appointment();
        Patient patient = patientRepository.findPatientByPatientId(appointmentScheduledEvent.getPatientId());

        appointment.setAppointmentId(appointmentScheduledEvent.getAppointmentId());
        appointment.setPatient(patient);
        appointment.setDoctorId(appointmentScheduledEvent.getDoctorId());
        appointment.setAppointmentDateTime(appointmentScheduledEvent.getAppointmentDateTime());
        appointment.setAppointmentReason(appointmentScheduledEvent.getReason());
        appointment.setStatus(appointmentScheduledEvent.getStatus().toString());
        appointment.setNotes(" ");
        appointment.setAppointCancellationReason("");

        appointmentRepository.save(appointment);

    }

    @EventHandler
    public void on(AppointmentRescheduledEvent appointmentRescheduledEvent) {
        log.info("Handling Appointment Rescheduled Event...");
        Appointment appointment = appointmentRepository.findAppointmentByAppointmentId(appointmentRescheduledEvent.getAppointmentId());

        appointment.setAppointmentDateTime(appointmentRescheduledEvent.getAppointmentDateTime());
        appointment.setStatus(appointmentRescheduledEvent.getStatus().toString());

        appointmentRepository.save(appointment);
    }

    @EventHandler
    public void on(AppointmentCancelledEvent appointmentCancelledEvent) {
        log.info("Handling Appointment Cancelled Event...");
        Appointment appointment = appointmentRepository.findAppointmentByAppointmentId(appointmentCancelledEvent.getAppointmentId());
        appointment.setStatus(appointmentCancelledEvent.getStatus().toString());
        appointment.setAppointCancellationReason(appointmentCancelledEvent.getCancellationReason());
        appointmentRepository.save(appointment);

    }

    @EventHandler
    public void on(AppointmentCompletedEvent appointmentCompletedEvent) {
        log.info("Handling Appointment Completed Event...");
        Appointment appointment = appointmentRepository.findAppointmentByAppointmentId(appointmentCompletedEvent.getAppointmentId());
        appointment.setStatus(appointmentCompletedEvent.getStatus().toString());
        appointment.setNotes(appointmentCompletedEvent.getCompleteNotes());
        appointmentRepository.save(appointment);

    }

}
