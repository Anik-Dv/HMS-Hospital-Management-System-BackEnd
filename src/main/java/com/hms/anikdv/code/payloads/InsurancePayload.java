package com.hms.anikdv.code.payloads;

import java.io.Serializable;
import java.time.LocalDate;

public class InsurancePayload implements Serializable {

    private Long insurance_id;
    private String policyNumber;
    private String provider;
    private String coverageDetails;
    private double claimAmount;

    private PatientPayload patient;

    private LocalDate claimDate;
    private String claimStatus;         // e.g., PENDING, APPROVED, DENIED

    public InsurancePayload() {
    }

    public InsurancePayload(Long insurance_id, String policyNumber, String provider, String coverageDetails, double claimAmount, PatientPayload patient, LocalDate claimDate, String claimStatus) {
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

    public PatientPayload getPatient() {
        return patient;
    }

    public void setPatient(PatientPayload patient) {
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
}
