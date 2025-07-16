package com.innovativecore.patientappointment.query;


import com.innovativecore.patientappointment.common.event.PatientRegisteredEvent;
import com.innovativecore.patientappointment.common.event.PatientUpdatedEvent;
import com.innovativecore.patientappointment.query.entity.Patient;
import com.innovativecore.patientappointment.query.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
         patient.setPatientId(patientRegisteredEvent.getId());
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
     public void on(PatientUpdatedEvent event) {
          log.info("Handling PatientUpdatedEvent for patient ID: {}", event.getId());

          try {
               Patient patient = patientRepository.findById(event.getId())
                       .orElseThrow(() -> new IllegalStateException(
                               "Patient not found for update: " + event.getId()));

               updatePatientFields(patient, event);

               patientRepository.save(patient);

               log.info("Successfully updated patient read model for ID: {}", event.getId());

          } catch (Exception e) {
               log.error("Error handling PatientUpdatedEvent for patient ID: {}",
                       event.getId(), e);
               throw e;
          }
     }

     private void updatePatientFields(Patient patient, PatientUpdatedEvent event) {
          event.getUpdatedFields().forEach((fieldName, fieldValue) -> {
               switch (fieldName) {
                    case "phoneNumber":
                         patient.setContactNumber(fieldValue);
                         break;
                    case "dateOfBirth":

                         patient.setDateOfBirth(LocalDate.parse(fieldValue));
                         break;
                    case "address":
                         patient.setAddress(fieldValue);
                    default:
                         log.warn("Unknown field updated: {} = {}", fieldName, fieldValue);
                         break;
               }
          });
     }
}


