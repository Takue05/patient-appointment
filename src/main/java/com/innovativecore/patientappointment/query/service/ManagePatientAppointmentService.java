package com.innovativecore.patientappointment.query.service;


import com.innovativecore.patientappointment.common.event.patient.PatientRegisteredEvent;
import com.innovativecore.patientappointment.common.event.patient.PatientUpdatedEvent;
import com.innovativecore.patientappointment.query.entity.Patient;
import com.innovativecore.patientappointment.query.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ManagePatientAppointmentService {
     private final PatientRepository patientRepository;

     public ManagePatientAppointmentService(PatientRepository patientRepository) {
         this.patientRepository = patientRepository;
     }

     @EventHandler
    public void on(PatientRegisteredEvent patientRegisteredEvent){
         log.info("Handling Patient Registered Event...");
         Patient patient = new  Patient();
         patient.setPatientId(patientRegisteredEvent.getPatientId());
         patient.setFirstName(patientRegisteredEvent.getFirstName());
         patient.setLastName(patientRegisteredEvent.getLastName());
         patient.setDateOfBirth(patientRegisteredEvent.getDateOfBirth());
         patient.setGender(patientRegisteredEvent.getGender());
         patient.setContactNumber(patientRegisteredEvent.getContactNumber());
         patient.setAddress(patientRegisteredEvent.getAddress());
         patient.setRegisteredAt(patientRegisteredEvent.getRegisteredAt());
         patientRepository.save(patient);

         log.info("Patient has been saved!");

     }

    @EventHandler
    public void on(PatientUpdatedEvent patientUpdatedEvent){
          log.info("Handling Patient Updated Event...");
          Patient patient = patientRepository.findPatientByPatientId(patientUpdatedEvent.getPatientId());
        patient.setAddress(patientUpdatedEvent.getAddress());
        patient.setContactNumber(patientUpdatedEvent.getContactNumber());
          patient.setGender(patientUpdatedEvent.getGender());

    }




}


