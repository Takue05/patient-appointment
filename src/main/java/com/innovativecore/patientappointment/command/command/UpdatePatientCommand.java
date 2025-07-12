package com.innovativecore.patientappointment.command.command;

import java.time.Instant;
import java.util.Optional;

public class UpdatePatientCommand extends BaseCommand <String> {

    private final Optional<String> contactNumber;
    private final Optional<String> address;
    private final Optional<String> gender;
    private final Instant updatedAt;
    private final String updatedBy;

    public UpdatePatientCommand(String id, String name, String gender, String contactNumber, String address, String updatedBy, Instant updatedAt) {
super(id);
        this.contactNumber = Optional.ofNullable(contactNumber);
        this.address = Optional.ofNullable(address);
this.gender = Optional.ofNullable(gender);
    this.updatedAt = updatedAt;
    this.updatedBy = updatedBy;
    }

    public Optional<String> getContactNumber() {
        return contactNumber;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Optional<String> getGender() {
        return gender;
    }

    public Optional<String> getAddress() {
        return address;
    }
}


