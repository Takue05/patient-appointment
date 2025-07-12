package com.innovativecore.patientappointment.command.controller;


import com.innovativecore.patientappointment.command.dto.RegisterPatientRequest;
import com.innovativecore.patientappointment.command.service.AppointmentCommandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value="/patient-appointment")
public class PatientAppointmentController {

    private final AppointmentCommandService appointmentCommandService;

    public PatientAppointmentController(AppointmentCommandService appointmentCommandService) {
        this.appointmentCommandService = appointmentCommandService;
    }

    @PostMapping(value ="/register-patient")
    public ResponseEntity<?> registerPatient(@RequestBody RegisterPatientRequest registerPatientRequest) {
        try {
            CompletableFuture<String> response = appointmentCommandService.registerPatient(registerPatientRequest);

            return new ResponseEntity<>(response.get(), HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}

