package com.hms.anikdv.code.payloads;

import com.hms.anikdv.code.entities.Patient;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

public class BillingPayload implements Serializable {
    private Long billing_id;

    private PatientPayload patientPayload;

    private Double amount;
    private Date billingDate;
    private String status;  // e.g., Pending, Paid
    private String insuranceClaimNumber;

    public BillingPayload() {
    }

    public BillingPayload(Long billing_id, PatientPayload patientPayload, Double amount, Date billingDate, String status, String insuranceClaimNumber) {
        this.billing_id = billing_id;
        this.patientPayload = patientPayload;
        this.amount = amount;
        this.billingDate = billingDate;
        this.status = status;
        this.insuranceClaimNumber = insuranceClaimNumber;
    }

    public Long getBilling_id() {
        return billing_id;
    }

    public void setBilling_id(Long billing_id) {
        this.billing_id = billing_id;
    }

    public PatientPayload getPatientPayload() {
        return patientPayload;
    }

    public void setPatientPayload(PatientPayload patientPayload) {
        this.patientPayload = patientPayload;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInsuranceClaimNumber() {
        return insuranceClaimNumber;
    }

    public void setInsuranceClaimNumber(String insuranceClaimNumber) {
        this.insuranceClaimNumber = insuranceClaimNumber;
    }
}
