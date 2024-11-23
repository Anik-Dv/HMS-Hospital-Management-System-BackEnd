package com.hms.anikdv.code.payloads;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hms.anikdv.code.entities.*;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class PatientPayload implements Serializable {

    private long patientId;
    private String currentHealthStatus;
    private String bloodGroup;
    private String urgency;

    private Set<AppointmentPayload> appointmentsPayload = new HashSet<>();

    private Set<MedicalRecordPayload> medicalRecordsPayload = new HashSet<>();

    private Set<PrescriptionPayload> prescriptionsPayload = new HashSet<>();

    private Set<BillingPayload> billingsPayload = new HashSet<>();

    private Set<LabTestPayload> labTestsPayload = new HashSet<>();

    private Set<InsurancePayload> insurancesPayload = new HashSet<>();

    private Set<MedicationPayload> medicationPayload = new HashSet<>();

    private UserPayload userPayload;

    public PatientPayload() {
    }

    public PatientPayload(long patientId, String currentHealthStatus, String bloodGroup, String urgency, Set<AppointmentPayload> appointmentsPayload, Set<MedicalRecordPayload> medicalRecordsPayload, Set<PrescriptionPayload> prescriptionsPayload, Set<BillingPayload> billingsPayload, Set<LabTestPayload> labTestsPayload, Set<InsurancePayload> insurancesPayload, Set<MedicationPayload> medicationPayload, UserPayload userPayload) {
        this.patientId = patientId;
        this.currentHealthStatus = currentHealthStatus;
        this.bloodGroup = bloodGroup;
        this.urgency = urgency;
        this.appointmentsPayload = appointmentsPayload;
        this.medicalRecordsPayload = medicalRecordsPayload;
        this.prescriptionsPayload = prescriptionsPayload;
        this.billingsPayload = billingsPayload;
        this.labTestsPayload = labTestsPayload;
        this.insurancesPayload = insurancesPayload;
        this.medicationPayload = medicationPayload;
        this.userPayload = userPayload;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public String getCurrentHealthStatus() {
        return currentHealthStatus;
    }

    public void setCurrentHealthStatus(String currentHealthStatus) {
        this.currentHealthStatus = currentHealthStatus;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public Set<AppointmentPayload> getAppointmentsPayload() {
        return appointmentsPayload;
    }

    public void setAppointmentsPayload(Set<AppointmentPayload> appointmentsPayload) {
        this.appointmentsPayload = appointmentsPayload;
    }

    public Set<MedicalRecordPayload> getMedicalRecordsPayload() {
        return medicalRecordsPayload;
    }

    public void setMedicalRecordsPayload(Set<MedicalRecordPayload> medicalRecordsPayload) {
        this.medicalRecordsPayload = medicalRecordsPayload;
    }

    public Set<PrescriptionPayload> getPrescriptionsPayload() {
        return prescriptionsPayload;
    }

    public void setPrescriptionsPayload(Set<PrescriptionPayload> prescriptionsPayload) {
        this.prescriptionsPayload = prescriptionsPayload;
    }

    public Set<BillingPayload> getBillingsPayload() {
        return billingsPayload;
    }

    public void setBillingsPayload(Set<BillingPayload> billingsPayload) {
        this.billingsPayload = billingsPayload;
    }

    public Set<LabTestPayload> getLabTestsPayload() {
        return labTestsPayload;
    }

    public void setLabTestsPayload(Set<LabTestPayload> labTestsPayload) {
        this.labTestsPayload = labTestsPayload;
    }

    public Set<InsurancePayload> getInsurancesPayload() {
        return insurancesPayload;
    }

    public void setInsurancesPayload(Set<InsurancePayload> insurancesPayload) {
        this.insurancesPayload = insurancesPayload;
    }

    public Set<MedicationPayload> getMedicationPayload() {
        return medicationPayload;
    }

    public void setMedicationPayload(Set<MedicationPayload> medicationPayload) {
        this.medicationPayload = medicationPayload;
    }

    public UserPayload getUserPayload() {
        return userPayload;
    }

    public void setUserPayload(UserPayload userPayload) {
        this.userPayload = userPayload;
    }
}
