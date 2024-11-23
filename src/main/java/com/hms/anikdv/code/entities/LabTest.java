package com.hms.anikdv.code.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * @info This is a LabTest Entity Class
 * @category Model Class
 */
@Entity
public class LabTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lab_id;

    private String testName;
    private double price;
    private String sampleType;              // e.g., Blood, Urine
    private LocalDate sampleCollectionDate;
    private LocalDate testDate;
    private String result;
    private String status;                  // e.g., PENDING, COMPLETED, IN_PROGRESS

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    public LabTest() {
    }

    public LabTest(Long lab_id, String testName, double price, String sampleType, LocalDate sampleCollectionDate, LocalDate testDate, String result, String status, Patient patient) {
        this.lab_id = lab_id;
        this.testName = testName;
        this.price = price;
        this.sampleType = sampleType;
        this.sampleCollectionDate = sampleCollectionDate;
        this.testDate = testDate;
        this.result = result;
        this.status = status;
        this.patient = patient;
    }

    public Long getLab_id() {
        return lab_id;
    }

    public void setLab_id(Long lab_id) {
        this.lab_id = lab_id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public LocalDate getSampleCollectionDate() {
        return sampleCollectionDate;
    }

    public void setSampleCollectionDate(LocalDate sampleCollectionDate) {
        this.sampleCollectionDate = sampleCollectionDate;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "LabTest{" +
                "lab_id=" + lab_id +
                ", testName='" + testName + '\'' +
                ", price=" + price +
                ", sampleType='" + sampleType + '\'' +
                ", sampleCollectionDate=" + sampleCollectionDate +
                ", testDate=" + testDate +
                ", result='" + result + '\'' +
                ", status='" + status + '\'' +
                ", patient=" + patient +
                '}';
    }
}