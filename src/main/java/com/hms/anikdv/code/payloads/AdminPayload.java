package com.hms.anikdv.code.payloads;

import com.hms.anikdv.code.entities.User;

import java.time.LocalDateTime;

public class AdminPayload {

    private Long adminid;
    private UserPayload userPayload;
    private LocalDateTime createdAt;
    private String department;

    public AdminPayload() {
    }

    public AdminPayload(Long adminid, UserPayload userPayload, LocalDateTime createdAt, String department) {
        this.adminid = adminid;
        this.userPayload = userPayload;
        this.createdAt = createdAt;
        this.department = department;
    }

    public Long getAdminid() {
        return adminid;
    }

    public void setAdminid(Long adminid) {
        this.adminid = adminid;
    }

    public UserPayload getUserPayload() {
        return userPayload;
    }

    public void setUserPayload(UserPayload userPayload) {
        this.userPayload = userPayload;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
