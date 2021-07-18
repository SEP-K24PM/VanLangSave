package com.vls.managementservice.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "user_handling")
public class UserHandling {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "reason")
    private String reason;

    @Column(name = "user_id")
    private UUID user_id;

    @Column(name = "admin_id")
    private UUID admin_id;

    @Column(name = "time")
    private Date time;

    public UserHandling() {
    }

    public UserHandling(UUID id, String reason, UUID user_id, UUID admin_id, Date time) {
        this.id = id;
        this.reason = reason;
        this.user_id = user_id;
        this.admin_id = admin_id;
        this.time = time;
    }

    public UserHandling(String reason, UUID user_id, UUID admin_id, Date time) {
        this.reason = reason;
        this.user_id = user_id;
        this.admin_id = admin_id;
        this.time = time;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public UUID getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(UUID admin_id) {
        this.admin_id = admin_id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
