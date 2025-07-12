package com.innovativecore.patientappointment.query;


import com.innovativecore.patientappointment.common.event.PatientRegisteredEvent;
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
         patient.setPatientId(patientRegisteredEvent.getId());
         patient.setFirstName(patientRegisteredEvent.getFirstName());
         patient.setLastName(patientRegisteredEvent.getLastName());
         patient.setDateOfBirth(patientRegisteredEvent.getDateOfBirth());
         patient.setGender(patientRegisteredEvent.getGender());
         patient.setContactNumber(patientRegisteredEvent.getContactNumber());
         patient.setAddress(patientRegisteredEvent.getAddress());
         patient.setRegisteredAt(patientRegisteredEvent.getRegisteredAt());
         patientRepository.save(patient);

     }
}
