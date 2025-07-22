package com.innovativecore.patientappointment.config;

import ca.uhn.fhir.context.FhirContext;
import org.hl7.fhir.r4.formats.IParser;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


    @Configuration
    @EnableConfigurationProperties
    public class FhirR4Config {

        @Bean
        public FhirContext fhirContext() {
            FhirContext ctx = FhirContext.forR4();
            ctx.getRestfulClientFactory().setSocketTimeout(60000);
            return ctx;
        }

        @Bean
        public IParser jsonParser(FhirContext fhirContext) {
            return (IParser) fhirContext.newJsonParser().setPrettyPrint(true);
        }

        @Bean
        public IParser xmlParser(FhirContext fhirContext) {
            return (IParser) fhirContext.newXmlParser().setPrettyPrint(true);
        }
    }

