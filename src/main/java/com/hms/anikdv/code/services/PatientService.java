package com.hms.anikdv.code.services;

import com.hms.anikdv.code.exceptions.ResourceNotFoundException;
import com.hms.anikdv.code.payloads.PatientPayload;
import com.hms.anikdv.code.payloads.UserPayload;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * This is Declaration of Patient Service
 *
 * @author AnikDV
 */
public interface PatientService {

    /**
     * This Method For Registration Patient
     *
     * @param userPayload
     * @return a new registered patient
     */
    @CacheEvict(value = "patient", allEntries = true)
    UserPayload registerPatient(final UserPayload userPayload);

    /**
     * This Method For Update Patient
     *
     * @param patientPayload
     * @param patientId
     * @return updated Patient
     * @throws Exception
     */
    @CacheEvict(value = "patient", allEntries = true)
    PatientPayload updatePatient(final Long patientId, final Long userId, final PatientPayload patientPayload) throws Exception;


    /**
     * This Method For Delete Patient
     *
     * @param patientId
     * @return Delete Patient
     * @throws Exception
     */
    @CacheEvict(value = "patient", key = "#patientId", beforeInvocation = true)
    boolean deletePatient(final Long patientId) throws ResourceNotFoundException;


    /**
     * This Method For getting all Patient
     *
     * @return All Patients
     */
    @Cacheable(value = "Patient")
    List<PatientPayload> GetAllPatients();

    /**
     * This Method For get Patient
     *
     * @param patientId
     * @return a Patient.
     */
    @Cacheable(value = "patient")
    PatientPayload GetPatientById(final Long patientId);

}
