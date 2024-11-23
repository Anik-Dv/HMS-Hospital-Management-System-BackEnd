package com.hms.anikdv.code.controllers;

import com.hms.anikdv.code.entities.User;
import com.hms.anikdv.code.payloads.ApiResponse;
import com.hms.anikdv.code.payloads.DoctorPayload;
import com.hms.anikdv.code.payloads.PatientPayload;
import com.hms.anikdv.code.payloads.UserPayload;
import com.hms.anikdv.code.services.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {
    final static private Logger logger = LoggerFactory.getLogger(DoctorController.class);
    @Autowired
    private DoctorService doctorService;


    /**
     * @info This Method for Update Patient Information's
     * @param doctorPayload
     * @param doctorId
     * @return Status with Updated Patient | NOT NULL
     */
    @Tag(name = "Doctor API", description = "All Methods of Doctor's APIs")
    @Operation(summary = "Update Doctor Information's",
            description = "Update Doctor information's. The response is updated Doctor object with id, name, and other details.")
    @PutMapping(value = "/update/{doctorId}", consumes = "application/json")
    private ResponseEntity<?> updateDoctorHandler(@Valid @RequestBody DoctorPayload doctorPayload, @PathVariable Long doctorId) {
        final String METHOD_NAME = "updateDoctorHandler";
        logger.info("Method Invoked: " + this.getClass().getName() + ":" + METHOD_NAME);
        try {
            DoctorPayload updatedDoctor = this.doctorService.updateDoctor(doctorPayload, doctorId);
            logger.info("Doctor id is : {} " + doctorPayload.getDoctorId());
            logger.info("Response The Method: " + this.getClass().getName() + ":" + METHOD_NAME);
            return ResponseEntity.status(HttpStatus.OK).body(updatedDoctor);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(ex.getMessage() + "| Invalid Request!", false));
        }
    }

    /**
     * @info This Method for Delete Doctor
     * @param doctorId
     * @return Status with Deleted Doctor | NOT NULL
     */
    @Tag(name = "Doctor API", description = "All Methods of Doctor's APIs")
    @Operation(summary = "Delete Doctor Information's",
            description = "Delete Doctor information's. The response is Delete Doctor object with id, name, and other details.")
    @DeleteMapping(value = "/delete/{doctorId}")
    private ResponseEntity<?> deleteDoctorHandler(@Valid @PathVariable Long doctorId) {
        final String METHOD_NAME = "deleteDoctorHandler";
        logger.info("Method Invoked: " + this.getClass().getName() + ":" + METHOD_NAME);
        try {
            boolean deleteDoctor = this.doctorService.deleteDoctor(doctorId);
            logger.info("Response The Method: " + this.getClass().getName() + ":" + METHOD_NAME);
            if (deleteDoctor) {
                return ResponseEntity.status(HttpStatus.OK).body("Doctor Has Been deleted Successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body("Doctor Not deleted! Something Went Wrong!");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(ex.getMessage() + "| Invalid Request!", false));
        }
    }

    /**
     * @info This Method for Get All Doctors
     * @return Status with All Doctors | NOT NULL
     */
    @Tag(name = "Doctor API", description = "All Methods of Doctor's APIs")
    @Operation(summary = "Get All Doctors Information's",
            description = "Get Doctors information's. The response is Get Doctors object with id, name, and other details.")
    @GetMapping(value = "/")
    private ResponseEntity<List<?>> getDoctorsHandler() {
        final String METHOD_NAME = "getDoctorsHandler";
        logger.info("Method Invoked: " + this.getClass().getName() + ":" + METHOD_NAME);
        try {
            List<DoctorPayload> allDoctors = this.doctorService.GetAllDoctors();
            logger.info("Response The Method: " + this.getClass().getName() + ":" + METHOD_NAME);
            return ResponseEntity.status(HttpStatus.FOUND).body(allDoctors);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * @info This Method for Get Single Doctors
     * @return Status with Single Doctors | NOT NULL
     */
    @Tag(name = "Doctor API", description = "All Methods of Doctor's APIs")
    @Operation(summary = "Get Single Doctors Information's",
            description = "Get Single Doctor information's. The response is Get Single Doctor object with id, name, and other details.")
    @GetMapping(value = "/{doctorId}")
    private ResponseEntity<?> GetDoctorHandler(@Valid @PathVariable Long doctorId) {
        final String METHOD_NAME = "GetDoctorHandler";
        logger.info("Method Invoked: " + this.getClass().getName() + ":" + METHOD_NAME);
        try {
            DoctorPayload doctor = this.doctorService.GetDoctorById(doctorId);
            logger.info("Response The Method: " + this.getClass().getName() + ":" + METHOD_NAME);
            return ResponseEntity.status(HttpStatus.FOUND).body(doctor);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(ex.getMessage() + "| Something Went Wrong!", false));
        }
    }

}
