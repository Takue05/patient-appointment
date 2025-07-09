package com.innovativecore.patientappointment.command.service;

import com.innovativecore.patientappointment.command.command.RegisterPatientCommand;
import com.innovativecore.patientappointment.command.dto.RegisterPatientRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AppointmentCommandService {

    private final CommandGateway commandGateway;

    public AppointmentCommandService(CommandGateway commandGateway) {
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
                registerPatientRequest.getAddress()
        ));

    }


}




