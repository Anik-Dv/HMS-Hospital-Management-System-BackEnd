package com.hms.anikdv.code.services;

import com.hms.anikdv.code.payloads.DoctorPayload;
import com.hms.anikdv.code.payloads.UserPayload;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * This is Declaration of Doctor Service
 *
 * @author AnikDV
 */
public interface DoctorService {

    /**
     * This Method For Registration Doctor
     *
     * @param userPayload
     * @return a new registered doctor
     */
    @CacheEvict(value = "doctor", allEntries = true)
    UserPayload registerDoctor(final UserPayload userPayload);

    /**
     * This Method For getting all Doctor
     *
     * @return All users
     */
    @Cacheable(value = "doctor")
    List<DoctorPayload> GetAllDoctors();

    /**
     * This Method For Update Doctor
     *
     * @param doctorPayload
     * @param doctorId
     * @return updated doctor
     * @throws Exception
     */
    @CacheEvict(value = "doctor", allEntries = true)
    DoctorPayload updateDoctor(final DoctorPayload doctorPayload, final Long doctorId) throws Exception;

    /**
     * This Method For Delete Doctor
     *
     * @param doctorId
     * @return deleted doctor user or not e.g: True/False
     * @throws Exception
     */
    @CacheEvict(value = "doctor", allEntries = true)
    boolean deleteDoctor(final Long doctorId) throws Exception;


    /**
     * This Method For get doctor
     *
     * @param doctorId
     * @return a doctor user.
     */
    @Cacheable(value = "doctor")
    DoctorPayload GetDoctorById(final Long doctorId);

}
