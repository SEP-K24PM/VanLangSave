package com.vls.managementservice.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "notification")
public class Notification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "url")
    private String url;

    @Column(name = "time")
    private Date time;

    @Column(name = "user_id")
    private UUID user_id;

    public Notification() {
    }

    public Notification(UUID id, String description, String url, Date time, UUID user_id) {
        this.id = id;
        this.description = description;
        this.url = url;
        this.time = time;
        this.user_id = user_id;
    }

    public Notification(String description, String url, Date time, UUID user_id) {
        this.description = description;
        this.url = url;
        this.time = time;
        this.user_id = user_id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

}
