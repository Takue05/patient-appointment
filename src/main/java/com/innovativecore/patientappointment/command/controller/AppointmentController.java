package com.innovativecore.patientappointment.command.controller;

import com.innovativecore.patientappointment.command.dto.appointmentDTO.CancelAppointmentRequest;
import com.innovativecore.patientappointment.command.dto.appointmentDTO.CompleteAppointmentRequest;
import com.innovativecore.patientappointment.command.dto.appointmentDTO.RescheduleAppointmentRequest;
import com.innovativecore.patientappointment.command.dto.appointmentDTO.ScheduleAppointmentRequest;
import com.innovativecore.patientappointment.command.service.AppointmentCommandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping(value= "/appointment")
public class AppointmentController {

    AppointmentCommandService appointmentCommandService;

    public AppointmentController(AppointmentCommandService appointmentCommandService) {
        this.appointmentCommandService = appointmentCommandService;
    }
    @PostMapping(value = "schedule-appointment")
    public ResponseEntity<?>scheduleAppointment(@RequestBody ScheduleAppointmentRequest scheduleAppointmentRequest) {
        try{
            CompletableFuture<String> response = appointmentCommandService.scheduleAppointment(scheduleAppointmentRequest);

            return new ResponseEntity<>(response.get(), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while trying to schedule an appointment", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/reschedule-appointment")
    public ResponseEntity<?>rescheduleAppointment(@RequestBody RescheduleAppointmentRequest rescheduleAppointmentRequest) {
        try{
            CompletableFuture<String> response = appointmentCommandService.rescheduleAppointment(rescheduleAppointmentRequest);
            return new ResponseEntity<>(response.get(), HttpStatus.ACCEPTED);
        }catch (Exception e) {
            return new ResponseEntity<>("An error occurred while trying to reschedule an appointment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/complete-appointment")
    public ResponseEntity<?>completeAppointment(@RequestBody CompleteAppointmentRequest completeAppointmentRequest) {
        try {
            CompletableFuture<String> response = appointmentCommandService.completeAppointment(completeAppointmentRequest);
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("An error occurred while trying to capture complete appointment", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping(value = "/cancel-appointment")
    public ResponseEntity<?>cancelAppointment(@RequestBody CancelAppointmentRequest cancelAppointmentRequest) {
        try {
            CompletableFuture<String> response = appointmentCommandService.cancelAppointment(cancelAppointmentRequest);
            return new ResponseEntity<>(response.get(), HttpStatus.ACCEPTED);
        }catch(Exception e) {
            return new ResponseEntity<>("An error occurred while trying to cancel an appointment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
