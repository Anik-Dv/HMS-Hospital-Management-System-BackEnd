package com.hms.anikdv.code.payloads;

import java.io.Serializable;

public class RolePayload implements Serializable {
    private Long id;
    private String name;
    private UserPayload userPayload;

    public RolePayload() {
    }

    public RolePayload(Long id, String name, UserPayload userPayload) {
        this.id = id;
        this.name = name;
        this.userPayload = userPayload;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserPayload getUserPayload() {
        return userPayload;
    }

    public void setUserPayload(UserPayload userPayload) {
        this.userPayload = userPayload;
    }
}
