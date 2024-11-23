package com.hms.anikdv.code.payloads;

import com.hms.anikdv.code.entities.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class DoctorPayload implements Serializable {
    private Long doctorId;
    @NotEmpty(message = "Doctor Specialization Must Not be Empty!")
    private String specialization;
    @Size(min = 3, max = 3000, message = "Doctor About Must be Minimum 3 Chars and Maximum 3000 Chars!")
    private String about;

    private User user;

    private Set<AppointmentPayload> appointmentPayload = new HashSet<>();

    private Set<MedicationPayload> medicationsPayload = new HashSet<>();

    public DoctorPayload() {
    }

    public DoctorPayload(Long doctorId, String specialization, String about, User user, Set<AppointmentPayload> appointmentPayload, Set<MedicationPayload> medicationsPayload) {
        this.doctorId = doctorId;
        this.specialization = specialization;
        this.about = about;
        this.user = user;
        this.appointmentPayload = appointmentPayload;
        this.medicationsPayload = medicationsPayload;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<AppointmentPayload> getAppointmentPayload() {
        return appointmentPayload;
    }

    public void setAppointmentPayload(Set<AppointmentPayload> appointmentPayload) {
        this.appointmentPayload = appointmentPayload;
    }

    public Set<MedicationPayload> getMedicationsPayload() {
        return medicationsPayload;
    }

    public void setMedicationsPayload(Set<MedicationPayload> medicationsPayload) {
        this.medicationsPayload = medicationsPayload;
    }
}
