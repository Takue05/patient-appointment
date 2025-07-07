package com.innovativecore.patientappointment.command;


import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDate;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class PatientEvent {
    @AggregateIdentifier
    private UUID patientId;
    private  String firstName;
    private  String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String contactNumber;
    private String address;


    public PatientEvent() {
    }

    @CommandHandler
    public PatientEvent(RegisterPatientCommand command) {
        apply(new PatientRegisteredEvent(
                command.getFirstName(),
                command.getLastName(),
                command.getDateOfBirth(),
                command.getGender(),
                command.getContactNumber(),
                command.getAddress()

                )
        );

    }

    @EventSourcingHandler
    public void on(PatientRegisteredEvent event) {
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.dateOfBirth = event.getDateOfBirth();
        this.gender = event.getGender();
        this.contactNumber = event.getContactNumber();
        this.address = event.getAddress();
    }



}
