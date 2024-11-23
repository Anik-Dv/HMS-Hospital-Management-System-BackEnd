package com.hms.anikdv.code.entities;

import jakarta.persistence.*;

/**
 * This is a Prescription Entity Class
 */
@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescription_id;

    @ManyToOne
   // @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;
    private String medication;
    private String instructions;

    @ManyToOne
    private Patient patient;

    public Prescription(){

    };
    public Prescription(Long prescription_id, Appointment appointment, String medication, String instructions) {
        this.prescription_id = prescription_id;
        this.appointment = appointment;
        this.medication = medication;
        this.instructions = instructions;
    }

    public Long getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(Long prescription_id) {
        this.prescription_id = prescription_id;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescription_id=" + prescription_id +
                ", appointment=" + appointment +
                ", medication='" + medication + '\'' +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}
