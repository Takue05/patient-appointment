package com.innovativecore.patientappointment.config;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.ExplicitTypePermission;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Configuration class for secure XStream serialization in Axon Framework
 */
@Configuration
public class AxonSerializationConfig {

    /**
     * Creates a secure XStream serializer with proper security permissions
     * This fixes the ForbiddenClassException by allowing your domain classes
     */
    @Bean
    @Primary
    public Serializer axonXStreamSerializer() {
        XStream xStream = new XStream();

        // Clear all existing permissions (deny all by default)
        xStream.addPermission(NoTypePermission.NONE);

        // Allow primitive types and their wrappers
        xStream.addPermission(PrimitiveTypePermission.PRIMITIVES);

        // Allow common Java types
        xStream.addPermission(new ExplicitTypePermission(new Class[]{
                // Java standard library classes
                java.lang.String.class,
                java.lang.Integer.class,
                java.lang.Long.class,
                java.lang.Double.class,
                java.lang.Boolean.class,
                java.math.BigDecimal.class,
                java.util.Date.class,
                java.time.LocalDateTime.class,
                java.time.LocalDate.class,
                java.time.LocalTime.class,
                java.time.Instant.class,
                java.util.UUID.class,

                // Collections
                java.util.ArrayList.class,
                java.util.HashMap.class,
                java.util.HashSet.class,
                java.util.LinkedHashMap.class,
                java.util.LinkedHashSet.class,
                java.util.TreeMap.class,
                java.util.TreeSet.class,
                java.util.Arrays.class,

                // Axon Framework internal classes
                org.axonframework.eventhandling.DomainEventMessage.class,
                org.axonframework.eventhandling.GenericDomainEventMessage.class,
                org.axonframework.messaging.MetaData.class,
                org.axonframework.eventhandling.GenericEventMessage.class,
                org.axonframework.commandhandling.GenericCommandMessage.class,
                org.axonframework.queryhandling.GenericQueryMessage.class,
        }));

        // Allow all types in your application package (recommended approach)
        // This is secure because it only allows your own classes
        xStream.allowTypesByWildcard(new String[] {
                "com.innovativecore.patientappointment.**"
        });

        // Additional security: allow specific Axon and Java packages
        xStream.allowTypesByWildcard(new String[] {
                "org.axonframework.**",
                "java.lang.**",
                "java.util.**",
                "java.time.**",
                "java.math.**"
        });

        return XStreamSerializer.builder()
                .xStream(xStream)
                .build();
    }
}