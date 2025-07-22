package com.innovativecore.patientappointment.command.dto.patientDTO;

import lombok.Data;

@Data
public class UpdatePatientRequest {

    private String patientId;
    private  String address;
    private  String contactNumber;
    private  String gender;
}
