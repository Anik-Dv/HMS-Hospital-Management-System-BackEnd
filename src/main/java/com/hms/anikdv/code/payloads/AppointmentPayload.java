package com.hms.anikdv.code.payloads;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.hms.anikdv.code.entities.Doctor;
import com.hms.anikdv.code.entities.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;

/**
 * This is a category DTO of category entity
 *
 * @author AnikDV
 */
public class AppointmentPayload implements Serializable {

    private Long appointment_id;
//    private Long doctorId;       // Doctor ID for the appointment

    @NotEmpty(message = "Reason Must Not be Empty!")
    @Size(min = 3, max = 300, message = "Your Appointment Reason Must be Minimum 3 Chars and Maximum 300 Chars!")
    private String reason;

    private PatientPayload patientPayload;

    private DoctorPayload doctorPayload;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date appointmentDate;
    private String status;  // e.g., Scheduled, Completed, Canceled

    public AppointmentPayload() {
    }

    public AppointmentPayload(Long appointment_id, Long doctorId, String reason, PatientPayload patientPayload, DoctorPayload doctorPayload, Date appointmentDate, String status) {
        this.appointment_id = appointment_id;
        //this.doctorId = doctorId;
        this.reason = reason;
        this.patientPayload = patientPayload;
        this.doctorPayload = doctorPayload;
        this.appointmentDate = appointmentDate;
        this.status = status;
    }

    public Long getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(Long appointment_id) {
        this.appointment_id = appointment_id;
    }

//    public Long getDoctorId() {
//        return doctorId;
//    }
//
//    public void setDoctorId(Long doctorId) {
//        this.doctorId = doctorId;
//    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public PatientPayload getPatientPayload() {
        return patientPayload;
    }

    public void setPatientPayload(PatientPayload patientPayload) {
        this.patientPayload = patientPayload;
    }

    public DoctorPayload getDoctorPayload() {
        return doctorPayload;
    }

    public void setDoctorPayload(DoctorPayload doctorPayload) {
        this.doctorPayload = doctorPayload;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
