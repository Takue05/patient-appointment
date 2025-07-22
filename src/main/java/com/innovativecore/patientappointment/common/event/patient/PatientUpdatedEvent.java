package com.innovativecore.patientappointment.common.event.patient;

import lombok.Value;


@Value
public class PatientUpdatedEvent{
    String patientId;
    String address;
    String contactNumber;
    String gender;


        public PatientUpdatedEvent(String patientId, String address, String contactNumber ,String gender) {
         this.patientId = patientId;
         this.address = address;
         this.gender = gender;
         this.contactNumber = contactNumber;
        }

}

