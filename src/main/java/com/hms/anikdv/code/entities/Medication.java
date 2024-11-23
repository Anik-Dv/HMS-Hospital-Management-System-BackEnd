package com.hms.anikdv.code.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * This is a Medication Entity Class
 */
@Entity
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medication_id;

    private String drugName;
    private int dosage;
    private String instructions;

    @ManyToOne
   // @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
   // @JoinColumn(name = "doctor_id", nullable = false)
    @JsonBackReference
    private Doctor doctor;

    private LocalDate datePrescribed;

    public Medication() {
    }

    public Medication(Long medication_id, String drugName, int dosage, String instructions, Patient patient, Doctor doctor, LocalDate datePrescribed) {
        this.medication_id = medication_id;
        this.drugName = drugName;
        this.dosage = dosage;
        this.instructions = instructions;
        this.patient = patient;
        this.doctor = doctor;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDate getDatePrescribed() {
        return datePrescribed;
    }

    public void setDatePrescribed(LocalDate datePrescribed) {
        this.datePrescribed = datePrescribed;
    }
}
