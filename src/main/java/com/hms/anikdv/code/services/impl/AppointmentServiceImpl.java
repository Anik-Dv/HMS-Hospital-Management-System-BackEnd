package com.hms.anikdv.code.services.impl;

import com.hms.anikdv.code.configuration.ModelMapperConfiguration;
import com.hms.anikdv.code.entities.Appointment;
import com.hms.anikdv.code.entities.Doctor;
import com.hms.anikdv.code.entities.Patient;
import com.hms.anikdv.code.exceptions.ResourceNotFoundException;
import com.hms.anikdv.code.payloads.AppointmentPayload;
import com.hms.anikdv.code.repositories.AppointmentRepository;
import com.hms.anikdv.code.repositories.DoctorRepository;
import com.hms.anikdv.code.repositories.PatientRepository;
import com.hms.anikdv.code.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ModelMapperConfiguration modelMapperConfiguration;

    /**
     * This Method For Schedule Appointment
     *
     * @param patientId
     * @param appointmentRequest
     * @return a new Scheduled Appointment
     */
    @Override
    public AppointmentPayload scheduleAppointment(Long patientId, AppointmentPayload appointmentRequest) {
        // Retrieve the patient from the database
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "not found with id: ", patientId));

        // Check if doctorId is present in appointmentRequest
        if (appointmentRequest.getDoctorPayload().getDoctorId() == null) {
            throw new IllegalArgumentException("Doctor ID must be provided for appointment scheduling.");
        }

        // Retrieve the doctor from the database
        Doctor doctor = doctorRepository.findById(appointmentRequest.getDoctorPayload().getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "not found with id: ", appointmentRequest.getDoctorPayload().getDoctorId()));

        // Create a new appointment
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setReason(appointmentRequest.getReason());
        appointment.setAppointmentDate(appointmentRequest.getAppointmentDate());
        appointment.setStatus("Scheduled");

        return this.modelMapperConfiguration.modelMapper().map(appointment, AppointmentPayload.class);
    }

    /**
     * This Method For Cancel Appointment
     *
     * @param patientId
     * @param doctorId
     * @return a new Cancel Scheduled Appointment
     */
    @Override
    public AppointmentPayload cancelScheduleAppointment(Long patientId, Long doctorId) {
        return null;
    }
}
