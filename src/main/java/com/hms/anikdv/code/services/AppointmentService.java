package com.hms.anikdv.code.services;

import com.hms.anikdv.code.payloads.AppointmentPayload;
import org.springframework.cache.annotation.CacheEvict;

/**
 * This is Declaration of Appointment Service
 *
 * @author AnikDV
 */
public interface AppointmentService {
    /**
     * This Method For Schedule Appointment
     *
     * @param patientId
     * @param appointmentRequest
     * @return a new Scheduled Appointment
     */
    @CacheEvict(value = "appointment", allEntries = true)
    AppointmentPayload scheduleAppointment(final Long patientId, final AppointmentPayload appointmentRequest);

    /**
     * This Method For Cancel Appointment
     *
     * @param patientId
     * @param doctorId
     * @return a new Cancel Scheduled Appointment
     */
    @CacheEvict(value = "appointment", allEntries = true)
    AppointmentPayload cancelScheduleAppointment(final Long patientId, final Long doctorId);

}
