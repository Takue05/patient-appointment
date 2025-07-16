package com.innovativecore.patientappointment.common.event;

import lombok.Getter;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;


@Getter
public class PatientUpdatedEvent extends BaseEvent<String>{

        private final Map<String, String> updatedFields;
        private final Instant updatedAt;
        private final String updatedBy;

        public PatientUpdatedEvent(String id,
                                   Map<String, String> updatedFields,
                                   Instant updatedAt,
                                   String updatedBy) {
            super(id);
            this.updatedFields = new HashMap<>(updatedFields);
            this.updatedAt = updatedAt;
            this.updatedBy = updatedBy;
        }

}

