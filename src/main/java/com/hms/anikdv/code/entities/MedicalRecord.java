package com.hms.anikdv.code.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicalRecord_id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    private String diagnosis;
    private String treatment;
    private Date visitDate;

    public MedicalRecord(Long medicalRecord_id, Patient patient, String diagnosis, String treatment, Date visitDate) {
        this.medicalRecord_id = medicalRecord_id;
        this.patient = patient;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.visitDate = visitDate;
    }

    public MedicalRecord() {

    }

    public Long getMedicalRecord_id() {
        return medicalRecord_id;
    }

    public void setMedicalRecord_id(Long medicalRecord_id) {
        this.medicalRecord_id = medicalRecord_id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }
}
