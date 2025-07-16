package com.innovativecore.patientappointment.command.controller;


import com.innovativecore.patientappointment.command.dto.RegisterPatientRequest;
import com.innovativecore.patientappointment.command.dto.UpdatePatientRequest;
import com.innovativecore.patientappointment.command.service.PatientCommandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value="/patient-appointment")
public class PatientAppointmentController {

    private final PatientCommandService appointmentCommandService;

    public PatientAppointmentController(PatientCommandService appointmentCommandService) {
        this.appointmentCommandService = appointmentCommandService;
    }

    @PostMapping(value ="/register-patient")
    public ResponseEntity<?> registerPatient(@RequestBody RegisterPatientRequest registerPatientRequest) {
        try {
            CompletableFuture<String> response = appointmentCommandService.registerPatient(registerPatientRequest);

            return new ResponseEntity<>(response.get(), HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>("An error occurred while trying to save patient", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/update-patient")
    public ResponseEntity<?> updatePatient(@RequestBody UpdatePatientRequest updatePatientRequest) {
        try {
            CompletableFuture<String> response = appointmentCommandService.updatePatient(updatePatientRequest);
            return new ResponseEntity<>(response.get(), HttpStatus.ACCEPTED);
        }catch (Exception e) {
            return new ResponseEntity<>("An error occurred while trying to update patient", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

