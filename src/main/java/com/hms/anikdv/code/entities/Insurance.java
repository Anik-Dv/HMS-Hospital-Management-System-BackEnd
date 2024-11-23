package com.hms.anikdv.code.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * @info This is a Insurance Entity Class
 * @category Model Class
 */
@Entity
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long insurance_id;
    private String policyNumber;
    private String provider;
    private String coverageDetails;
    private double claimAmount;

    @ManyToOne
    //@JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    private LocalDate claimDate;
    private String claimStatus;         // e.g., PENDING, APPROVED, DENIED

    public Insurance() {
    }

    public Insurance(Long insurance_id, String policyNumber, String provider, String coverageDetails, double claimAmount, Patient patient, LocalDate claimDate, String claimStatus) {
        this.insurance_id = insurance_id;
        this.policyNumber = policyNumber;
        this.provider = provider;
        this.coverageDetails = coverageDetails;
        this.claimAmount = claimAmount;
        this.patient = patient;
        this.claimDate = claimDate;
        this.claimStatus = claimStatus;
    }

    public Long getInsurance_id() {
        return insurance_id;
    }

    public void setInsurance_id(Long insurance_id) {
        this.insurance_id = insurance_id;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getCoverageDetails() {
        return coverageDetails;
    }

    public void setCoverageDetails(String coverageDetails) {
        this.coverageDetails = coverageDetails;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "insurance_id=" + insurance_id +
                ", policyNumber='" + policyNumber + '\'' +
                ", provider='" + provider + '\'' +
                ", coverageDetails='" + coverageDetails + '\'' +
                ", claimAmount=" + claimAmount +
                ", patient=" + patient +
                ", claimDate=" + claimDate +
                ", claimStatus='" + claimStatus + '\'' +
                '}';
    }
}
