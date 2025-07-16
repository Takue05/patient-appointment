package com.innovativecore.patientappointment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = {
		"com.innovativecore.patientappointment",
		"org.axonframework.eventsourcing.eventstore.jpa",
		"org.axonframework.modelling.saga.repository.jpa",
		"org.axonframework.eventhandling.tokenstore.jpa"
})
@EnableJpaRepositories(basePackages = "com.innovativecore.patientappointment")
@EnableTransactionManagement
public class PatientappointmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientappointmentApplication.class, args);
	}

}