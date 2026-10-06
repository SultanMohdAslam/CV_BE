package com.cv.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admin_security")
public class AdminSecurity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String passcode;

    public AdminSecurity() {
    }

    public AdminSecurity(String passcode) {
        this.passcode = passcode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }
}
