package com.innovativecore.patientappointment.command.aggregate;

import com.innovativecore.patientappointment.command.command.patient.RegisterPatientCommand;
import com.innovativecore.patientappointment.command.command.appointment.UpdatePatientCommand;
import com.innovativecore.patientappointment.common.event.PatientRegisteredEvent;
import com.innovativecore.patientappointment.common.event.PatientUpdatedEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Getter
@Aggregate
@Slf4j
public class PatientAggregate {

    @AggregateIdentifier
    private String patientId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String contactNumber;
    private String address;
    private Instant registeredAt;
    private Instant updatedAt;

    public PatientAggregate() {
    }

    @CommandHandler
    public PatientAggregate(RegisterPatientCommand registerPatientCommand) {
        log.debug("RegisterPatientCommand received for ID: {}", registerPatientCommand.getId());

        if (registerPatientCommand.getId() == null || registerPatientCommand.getId().isEmpty()) {
            throw new IllegalArgumentException("Patient ID cannot be null or empty");
        }

        apply(new PatientRegisteredEvent(
                registerPatientCommand.getId(),
                registerPatientCommand.getFirstName(),
                registerPatientCommand.getLastName(),
                registerPatientCommand.getDateOfBirth(),
                registerPatientCommand.getAddress(),
                registerPatientCommand.getGender(),
                registerPatientCommand.getContactNumber(),
                Instant.now()

        ));
    }

    @EventSourcingHandler
    public void on(PatientRegisteredEvent patientRegisteredEvent) {
        log.debug("PatientRegisteredEvent occurred for ID: {}", patientRegisteredEvent.getId());
        this.patientId = patientRegisteredEvent.getId();
        this.firstName = patientRegisteredEvent.getFirstName();
        this.lastName = patientRegisteredEvent.getLastName();
        this.dateOfBirth = patientRegisteredEvent.getDateOfBirth();
        this.gender = patientRegisteredEvent.getGender();
        this.contactNumber = patientRegisteredEvent.getContactNumber();
        this.address = patientRegisteredEvent.getAddress();
        this.registeredAt = patientRegisteredEvent.getRegisteredAt();

    }

    @CommandHandler
    public void handle(UpdatePatientCommand updatePatientCommand) {
        Map<String, String> updatedFields = new HashMap<>();
        if (updatePatientCommand.getContactNumber().isPresent() && !updatePatientCommand.getContactNumber().get().equals(this.contactNumber)){
            updatedFields.put("contactNumber", updatePatientCommand.getContactNumber().get());
        }
        if (updatePatientCommand.getAddress().isPresent() && !updatePatientCommand.getAddress().get().equals(this.address)){
            updatedFields.put("address", updatePatientCommand.getAddress().get());
        }
        if(updatePatientCommand.getGender().isPresent() && !updatePatientCommand.getGender().get().equals(this.gender)){
            updatedFields.put("gender", updatePatientCommand.getGender().get());
        }
        if(!updatedFields.isEmpty()){
            apply(new PatientUpdatedEvent(
                    updatePatientCommand.getId(),
                    updatedFields,
                    updatePatientCommand.getUpdatedAt(),
                    updatePatientCommand.getUpdatedBy()
            ));
        }

        }

    @EventSourcingHandler
    public void on(PatientUpdatedEvent event) {
        event.getUpdatedFields().forEach((field, value) -> {
            switch (field) {
                case "contactNumber":
                    this.contactNumber = value;
                    break;
                case "address":
                    this.address = value;
                    break;
                case "gender":
                    this.gender = value;
                    break;
            }
        });
        this.updatedAt = Instant.now();
    }

}