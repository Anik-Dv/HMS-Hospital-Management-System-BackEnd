package com.hms.anikdv.code.entities;

import jakarta.persistence.*;

import java.util.Date;

/**
 * @info This is a Billing Entity Class
 * @category Model Class
 */
@Entity
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billing_id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    private Double amount;
    private Date billingDate;
    private String status;  // e.g., Pending, Paid
    private String insuranceClaimNumber;

    public Billing() {
    }

    public Billing(Long id, Patient patient, Double amount, Date billingDate, String status, String insuranceClaimNumber) {
        this.billing_id = id;
        this.patient = patient;
        this.amount = amount;
        this.billingDate = billingDate;
        this.status = status;
        this.insuranceClaimNumber = insuranceClaimNumber;
    }

    public Long getBilling_id() {
        return billing_id;
    }

    public void setBilling_id(Long id) {
        this.billing_id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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
