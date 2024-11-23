package com.hms.anikdv.code.controllers.appointmentController;


import com.hms.anikdv.code.payloads.ApiResponse;
import com.hms.anikdv.code.payloads.AppointmentPayload;
import com.hms.anikdv.code.services.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController {
    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);
    @Autowired
    private AppointmentService appointmentService;

    /**
     * This Method For Schedule Appointment
     *
     * @param patientId
     * @param appointmentRequest
     * @return a new Scheduled Appointment
     */
    @Tag(name = "Appointment's API", description = "All Methods of Appointment's APIs")
    @Operation(summary = "Schedule Appointment Information's",
            description = "Schedule Appointment information's. The response is Schedule Appointment object with id, name, and other details.")
    @PostMapping(value = "/schedule/{patientId}", consumes = "application/json")
    public ResponseEntity<?> ScheduleAppointmentHandler(final @Valid @PathVariable Long patientId, final AppointmentPayload appointmentRequest) {
        final String METHOD_NAME = "ScheduleAppointmentHandler";
        logger.info("Method Invoked: " + this.getClass().getName() + ":" + METHOD_NAME);
        try {
            AppointmentPayload scheduleAppointment = this.appointmentService.scheduleAppointment(patientId, appointmentRequest);
            logger.info("Response The Method: " + this.getClass().getName() + ":" + METHOD_NAME);
            return ResponseEntity.status(HttpStatus.OK).body(scheduleAppointment);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(ex.getMessage() + " | Invalid Request!", false));
        }
    }



}
