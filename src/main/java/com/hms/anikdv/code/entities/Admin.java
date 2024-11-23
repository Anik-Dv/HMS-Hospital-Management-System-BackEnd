package com.hms.anikdv.code.entities;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

/*
* The Admin entity is linked to the User entity via a one-to-one relationship,
* representing an Admin as a special type of user.
* */
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminid;

    @OneToOne
    //@JoinColumn(name = "userid", referencedColumnName = "id")
    private User user;

    private String department;
    private LocalDateTime createdAt;

    // Constructors
    public Admin() {
        this.createdAt = LocalDateTime.now();
    }

    public Admin(User user, String department) {
        this.user = user;
        this.department = department;
        this.createdAt = LocalDateTime.now();
    }

    // getters, and setters
    public Long getId() {
        return adminid;
    }

    public void setId(Long adminid) {
        this.adminid = adminid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


}

