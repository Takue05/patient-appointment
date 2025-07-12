package com.innovativecore.patientappointment.query.controller;

import com.innovativecore.patientappointment.query.entity.Patient;
import com.innovativecore.patientappointment.query.query.FindPatientById;
import com.innovativecore.patientappointment.query.repository.PatientRepository;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/manage-patient")
public class ManagePatientAppointmentController {
    private final QueryGateway queryGateway;

    public ManagePatientAppointmentController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/get-patient")
    public ResponseEntity<Patient> getPatient(@RequestParam String patientId) {
        Patient patient  = queryGateway.query(
                new FindPatientById(patientId), Patient.class
        ).join();

        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(patient, HttpStatus.OK);

    }

}

