package com.hms.anikdv.code.controllers;

import com.hms.anikdv.code.payloads.ApiResponse;
import com.hms.anikdv.code.payloads.PatientPayload;
import com.hms.anikdv.code.payloads.UserPayload;
import com.hms.anikdv.code.services.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is Patient Rest Controller
 *
 * @author AnikDV
 */
@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {
    private Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;


    /**
     * @info This Method for Update Patient Information's
     * @param patientPayload
     * @return Status with Updated Patient | NOT NULL
     */
    @Tag(name = "Patient API", description = "All Methods of Patient's APIs")
    @Operation(summary = "Update Patient Information's",
            description = "Update Patient information's. The response is updated Patient object with id, name, and other details.")
    @PutMapping(value = "/update/{patientId}/{userId}", consumes = "application/json")
    private ResponseEntity<?> updatePatientInformation(final @Valid @PathVariable Long patientId, @PathVariable Long userId, @RequestBody PatientPayload patientPayload) throws Exception {
        final String METHOD_NAME = "updatePatientInformation";
        logger.info("Method Invoked: " + this.getClass().getName() + ":" + METHOD_NAME);

        try{
            PatientPayload updatedPatient = this.patientService.updatePatient(patientId,userId,patientPayload);
            logger.info("Patient id is : {} " + patientPayload.getPatientId());
            logger.info("Response The Method: " + this.getClass().getName() + ":" + METHOD_NAME);
            return ResponseEntity.status(HttpStatus.OK).body(updatedPatient);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(ex.getMessage() + "| Invalid Request!", false));
        }
    }

    /**
     * @info This Method for Delete Patient
     * @param patientId
     * @return Status with Deleted Patient | NOT NULL
     */
    @Tag(name = "Patient API", description = "All Methods of Patient's APIs")
    @Operation(summary = "Delete Patient Information's",
            description = "Delete Patient information's. The response is Delete Patient object with id, name, and other details.")
    @DeleteMapping(value = "/delete/{patientId}")
    private ResponseEntity<?> deletePatientInformation(final @Valid @PathVariable Long patientId) {
        final String METHOD_NAME = "deletePatientInformation";
        logger.info("Method Invoked: " + this.getClass().getName() + ":" + METHOD_NAME);
        try {
            boolean deletedPateint = this.patientService.deletePatient(patientId);
            logger.info("Response The Method: " + this.getClass().getName() + ":" + METHOD_NAME);
            if (deletedPateint) {
                return ResponseEntity.status(HttpStatus.OK).body("Patient Has Been deleted Successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body("Patient Not deleted! Something Went Wrong!");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(ex.getMessage() + "Something Went Wrong!", false));
        }
    }

    /**
     * @info This Method for Get All Patients
     * @return Status with All Patients Data | NOT NULL
     */
    @Tag(name = "Patient API", description = "All Methods of Patient's APIs")
    @Operation(summary = "Get All Patient Information's",
            description = "Get Patient information's. The response is All Patient object with id, name, and other details.")
    @GetMapping(value = "/")
    private ResponseEntity<List<?>> GetPatients() {
        final String METHOD_NAME = "GetPatients";
        logger.info("Method Invoked: " + this.getClass().getName() + ":" + METHOD_NAME);
        try {
            List<PatientPayload> patients = this.patientService.GetAllPatients();
            logger.info("Response The Method: " + this.getClass().getName() + ":" + METHOD_NAME);
            return ResponseEntity.status(HttpStatus.OK).body(patients);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * @info This Method for Get Single Patient
     * @return Status with Single Patient Data | NOT NULL
     */
    @Tag(name = "Patient API", description = "All Methods of Patient's APIs")
    @Operation(summary = "Get Single Patient Information's",
            description = "Get Patient information's. The response is Single Patient object with id, name, and other details.")
    @GetMapping(value = "/{patientId}")
    private ResponseEntity<?> GetSinglePatient(@Valid @PathVariable Long patientId) {
        final String METHOD_NAME = "GetSinglePatient";
        logger.info("Method Invoked: " + this.getClass().getName() + ":" + METHOD_NAME);
        try {
            PatientPayload patient = this.patientService.GetPatientById(patientId);
            logger.info("Response The Method: " + this.getClass().getName() + ":" + METHOD_NAME);
            return ResponseEntity.status(HttpStatus.OK).body(patient);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(ex.getMessage() + "| Something Went Wrong!", false));
        }
    }






























}
