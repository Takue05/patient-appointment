package com.innovativecore.patientappointment.command.aggregate;

import com.innovativecore.patientappointment.command.command.patient.RegisterPatientCommand;
import com.innovativecore.patientappointment.command.command.patient.UpdatePatientCommand;
import com.innovativecore.patientappointment.common.event.patient.PatientRegisteredEvent;
import com.innovativecore.patientappointment.common.event.patient.PatientUpdatedEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;
import java.time.LocalDate;

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
        log.debug("RegisterPatientCommand received for ID: {}", registerPatientCommand.getPatientId());

        if (registerPatientCommand.getPatientId() == null) {
            throw new IllegalArgumentException("Patient ID cannot be null or empty");
        }

        apply(new PatientRegisteredEvent(
                registerPatientCommand.getPatientId(),
                registerPatientCommand.getFirstName(),
                registerPatientCommand.getLastName(),
                registerPatientCommand.getDateOfBirth(),
                registerPatientCommand.getGender(),
                registerPatientCommand.getContactNumber(),
                registerPatientCommand.getAddress(),                Instant.now()

        ));
    }
    @CommandHandler
    public void handle(UpdatePatientCommand updatePatientCommand) {
        log.debug("UpdatePatientCommand received for ID: {}", updatePatientCommand.getPatientId());
        if (updatePatientCommand.getPatientId() == null) {
            throw new IllegalArgumentException("Patient ID cannot be null or empty");
        }
        apply(new PatientUpdatedEvent(
                updatePatientCommand.getPatientId(),
                updatePatientCommand.getAddress(),
                updatePatientCommand.getContactNumber(),
                updatePatientCommand.getGender()

        ));
    }
    @EventSourcingHandler
    public void on(PatientRegisteredEvent patientRegisteredEvent) {
        log.debug("PatientRegisteredEvent occurred for ID: {}", patientRegisteredEvent.getPatientId());
        this.patientId = patientRegisteredEvent.getPatientId();
        this.firstName = patientRegisteredEvent.getFirstName();
        this.lastName = patientRegisteredEvent.getLastName();
        this.dateOfBirth = patientRegisteredEvent.getDateOfBirth();
        this.gender = patientRegisteredEvent.getGender();
        this.contactNumber = patientRegisteredEvent.getContactNumber();
        this.address = patientRegisteredEvent.getAddress();
        this.registeredAt = patientRegisteredEvent.getRegisteredAt();

    }

}