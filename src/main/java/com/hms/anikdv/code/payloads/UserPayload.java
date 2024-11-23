package com.hms.anikdv.code.payloads;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UserPayload implements Serializable {
    private long userid;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dob;
    private String address;
    private Integer phoneNumber;
    private String email;
    private String password;
    private Set<RolePayload> roles = new HashSet<>();
    private PatientPayload patientPayload;
    private DoctorPayload doctorPayload;
    private AdminPayload adminPayload;


    public UserPayload() {
    }

    public UserPayload(long userid, String name, Date dob, String address, Integer phoneNumber, String email, String password, Set<RolePayload> roles, PatientPayload patientPayload, DoctorPayload doctorPayload, AdminPayload adminPayload) {
        this.userid = userid;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.patientPayload = patientPayload;
        this.doctorPayload = doctorPayload;
        this.adminPayload = adminPayload;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RolePayload> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolePayload> roles) {
        this.roles = roles;
    }

    public PatientPayload getPatientPayload() {
        return patientPayload;
    }

    public void setPatientPayload(PatientPayload patientPayload) {
        this.patientPayload = patientPayload;
    }

    public DoctorPayload getDoctorPayload() {
        return doctorPayload;
    }

    public void setDoctorPayload(DoctorPayload doctorPayload) {
        this.doctorPayload = doctorPayload;
    }

    public AdminPayload getAdminPayload() {
        return adminPayload;
    }

    public void setAdminPayload(AdminPayload adminPayload) {
        this.adminPayload = adminPayload;
    }
}
