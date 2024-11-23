package com.hms.anikdv.code.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @info This is a Patient Entity Class
 * @category Model Class
 */
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private long patientId;
    @Column(name = "current_health_status")
    private String currentHealthStatus;
    @Column(name = "blood_group")
    private String bloodGroup;
    private String urgency;

    @JsonIgnore
    @OneToMany(mappedBy = "patient" ,cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Appointment> appointments = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<MedicalRecord> medicalRecords = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Prescription> prescriptions = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Billing> billings = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<LabTest> labTests = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Insurance> insurances = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Medication> medications = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Patient() {
    }
    public Patient(long patientId, String currentHealthStatus, String bloodGroup, String urgency, Set<Appointment> appointments, Set<MedicalRecord> medicalRecords, Set<Prescription> prescriptions, User user) {
        this.patientId = patientId;
        this.currentHealthStatus = currentHealthStatus;
        this.bloodGroup = bloodGroup;
        this.urgency = urgency;
        this.appointments = appointments;
        this.medicalRecords = medicalRecords;
        this.prescriptions = prescriptions;
        this.user = user;
    }

    public Set<Billing> getBillings() {
        return billings;
    }

    public void setBillings(Set<Billing> billings) {
        this.billings = billings;
    }

    public Set<LabTest> getLabTests() {
        return labTests;
    }

    public void setLabTests(Set<LabTest> labTests) {
        this.labTests = labTests;
    }

    public Set<Insurance> getInsurances() {
        return insurances;
    }

    public void setInsurances(Set<Insurance> insurances) {
        this.insurances = insurances;
    }

    public Set<Medication> getMedications() {
        return medications;
    }

    public void setMedications(Set<Medication> medications) {
        this.medications = medications;
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

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Set<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(Set<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    public Set<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", currentHealthStatus='" + currentHealthStatus + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", urgency='" + urgency + '\'' +
                ", appointments=" + appointments +
                ", medicalRecords=" + medicalRecords +
                ", prescriptions=" + prescriptions +
                ", billings=" + billings +
                ", user=" + user +
                '}';
    }
}
