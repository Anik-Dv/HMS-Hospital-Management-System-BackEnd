package com.hms.anikdv.code.payloads;

import com.hms.anikdv.code.entities.Doctor;
import com.hms.anikdv.code.entities.Patient;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.time.LocalDate;

public class MedicationPayload implements Serializable {
    private Long medication_id;

    private String drugName;
    private int dosage;
    private String instructions;

    private PatientPayload patientPayload;

    private DoctorPayload doctorPayload;

    private LocalDate datePrescribed;

    public MedicationPayload() {
    }

    public MedicationPayload(Long medication_id, String drugName, int dosage, String instructions, PatientPayload patientPayload, DoctorPayload doctorPayload, LocalDate datePrescribed) {
        this.medication_id = medication_id;
        this.drugName = drugName;
        this.dosage = dosage;
        this.instructions = instructions;
        this.patientPayload = patientPayload;
        this.doctorPayload = doctorPayload;
        this.datePrescribed = datePrescribed;
    }

    public Long getMedication_id() {
        return medication_id;
    }

    public void setMedication_id(Long medication_id) {
        this.medication_id = medication_id;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
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

    public LocalDate getDatePrescribed() {
        return datePrescribed;
    }

    public void setDatePrescribed(LocalDate datePrescribed) {
        this.datePrescribed = datePrescribed;
    }
}
