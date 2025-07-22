package com.innovativecore.patientappointment.query.query;

import lombok.Data;

@Data
public class FindPatientById  {

    private String patientId;

    public FindPatientById(String patientId) {
        this.patientId = patientId;
    }
}
