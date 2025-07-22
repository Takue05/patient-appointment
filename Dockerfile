FROM openjdk:17-jdk

WORKDIR /app

COPY target/patientappointment-0.0.1-SNAPSHOT.jar app/patientappointment.jar

EXPOSE 8080

CMD ["java", "-jar", "patientappointment.jar"]

