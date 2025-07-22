package com.innovativecore.patientappointment.command.service;

import com.innovativecore.patientappointment.command.command.patient.UpdatePatientCommand;
import com.innovativecore.patientappointment.command.command.patient.RegisterPatientCommand;
import com.innovativecore.patientappointment.command.dto.patientDTO.RegisterPatientRequest;
import com.innovativecore.patientappointment.command.dto.patientDTO.UpdatePatientRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class PatientCommandService {

    private final CommandGateway commandGateway;

    public PatientCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<String> registerPatient(RegisterPatientRequest registerPatientRequest) {
        return commandGateway.send(new RegisterPatientCommand(
                UUID.randomUUID().toString(),
                registerPatientRequest.getFirstName(),
                registerPatientRequest.getLastName(),
                registerPatientRequest.getDateOfBirth(),
                registerPatientRequest.getGender(),
                registerPatientRequest.getContactNumber(),
                registerPatientRequest.getAddress(),
                registerPatientRequest.getRegisteredAt()

        ));
    }
    public CompletableFuture<String> updatePatient(UpdatePatientRequest updatePatientRequest) {
        return commandGateway.send(new UpdatePatientCommand(
                updatePatientRequest.getPatientId(),
                updatePatientRequest.getAddress(),
                updatePatientRequest.getContactNumber(),
                updatePatientRequest.getGender()

                )

        );
    }
}





