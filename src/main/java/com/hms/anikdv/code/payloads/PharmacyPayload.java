package com.hms.anikdv.code.payloads;

import java.io.Serializable;
import java.time.LocalDate;

public class PharmacyPayload implements Serializable {
    private Long pharma_id;

    private String drugName;
    private int quantity;
    private double pricePerUnit;
    private LocalDate expirationDate;

    public PharmacyPayload() {
    }

    public PharmacyPayload(Long pharma_id, String drugName, int quantity, double pricePerUnit, LocalDate expirationDate) {
        this.pharma_id = pharma_id;
        this.drugName = drugName;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.expirationDate = expirationDate;
    }

    public Long getPharma_id() {
        return pharma_id;
    }

    public void setPharma_id(Long pharma_id) {
        this.pharma_id = pharma_id;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
