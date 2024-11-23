package com.hms.anikdv.code.payloads;

import com.hms.anikdv.code.entities.Patient;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Date;

public class MedicalRecordPayload implements Serializable {
    private Long medicalRecord_id;

    private PatientPayload patientPayload;

    private String diagnosis;
    private String treatment;
    private Date visitDate;

    public MedicalRecordPayload() {
    }

    public MedicalRecordPayload(Long medicalRecord_id, PatientPayload patientPayload, String diagnosis, String treatment, Date visitDate) {
        this.medicalRecord_id = medicalRecord_id;
        this.patientPayload = patientPayload;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.visitDate = visitDate;
    }

    public Long getMedicalRecord_id() {
        return medicalRecord_id;
    }

    public void setMedicalRecord_id(Long medicalRecord_id) {
        this.medicalRecord_id = medicalRecord_id;
    }

    public PatientPayload getPatientPayload() {
        return patientPayload;
    }

    public void setPatientPayload(PatientPayload patientPayload) {
        this.patientPayload = patientPayload;
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
