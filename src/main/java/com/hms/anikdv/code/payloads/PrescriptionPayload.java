package com.hms.anikdv.code.payloads;

import com.hms.anikdv.code.entities.Appointment;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

public class PrescriptionPayload implements Serializable {
    private Long prescription_id;

    private AppointmentPayload appointmentPayload;
    private String medication;
    private String instructions;

    public PrescriptionPayload() {
    }

    public PrescriptionPayload(Long prescription_id, AppointmentPayload appointmentPayload, String medication, String instructions) {
        this.prescription_id = prescription_id;
        this.appointmentPayload = appointmentPayload;
        this.medication = medication;
        this.instructions = instructions;
    }

    public Long getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(Long prescription_id) {
        this.prescription_id = prescription_id;
    }

    public AppointmentPayload getAppointmentPayload() {
        return appointmentPayload;
    }

    public void setAppointmentPayload(AppointmentPayload appointmentPayload) {
        this.appointmentPayload = appointmentPayload;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
