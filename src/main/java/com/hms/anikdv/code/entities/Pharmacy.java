package com.hms.anikdv.code.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * @info This is a Pharmacy Entity Class
 * @category Model Class
 */
@Entity
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pharma_id;

    private String drugName;
    private int quantity;
    private double pricePerUnit;
    private LocalDate expirationDate;

    public Pharmacy() {
    }

    public Pharmacy(Long pharma_id, String drugName, int quantity, double pricePerUnit, LocalDate expirationDate) {
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

    @Override
    public String toString() {
        return "Pharmacy{" +
                "pharma_id=" + pharma_id +
                ", drugName='" + drugName + '\'' +
                ", quantity=" + quantity +
                ", pricePerUnit=" + pricePerUnit +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
