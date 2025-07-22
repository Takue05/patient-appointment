package com.innovativecore.patientappointment.command.controller;


import com.innovativecore.patientappointment.command.dto.patientDTO.RegisterPatientRequest;
import com.innovativecore.patientappointment.command.dto.patientDTO.UpdatePatientRequest;
import com.innovativecore.patientappointment.command.service.PatientCommandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value="/patient-appointment")
public class PatientController {

    private final PatientCommandService patientCommandService;

    public PatientController(PatientCommandService appointmentCommandService) {
        this.patientCommandService = appointmentCommandService;
    }

    @PostMapping(value ="/register-patient")
    public ResponseEntity<?> registerPatient(@RequestBody RegisterPatientRequest registerPatientRequest) {
        try {
            CompletableFuture<String> response = patientCommandService.registerPatient(registerPatientRequest);

            return new ResponseEntity<>(response.get(), HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>("An error occurred while trying to save patient", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/update-patient")
    public ResponseEntity<?> updatePatient(@RequestBody UpdatePatientRequest updatePatientRequest) {
        try {
            CompletableFuture<String> response = patientCommandService.updatePatient(updatePatientRequest);
            return new ResponseEntity<>(response.get(), HttpStatus.ACCEPTED);
        }catch (Exception e) {
            return new ResponseEntity<>("An error occurred while trying to update patient", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

