package com.innovativecore.patientappointment.command.command.patient;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class UpdatePatientCommand {
    @TargetAggregateIdentifier
    String patientId;
    String address;
    String contactNumber;
    String gender;

    }



