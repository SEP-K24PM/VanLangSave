package com.vls.thingservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "created_time")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date created_time;

    @Column(name = "thing_id")
    private UUID thing_id;

    @Column(name = "status")
    private String status;

    @Column(name = "exchange_method")
    private String exchange_method;

    @Column(name = "contact")
    private String contact;

    @Column(name = "visible")
    private boolean visible;

    @Column(name = "deletion")
    private boolean deletion;

    public Post() {
    }

    public Post(UUID id, String description, Date created_time, UUID thing_id, String status, String exchange_method,
            String contact, boolean visible, boolean deletion) {
        this.id = id;
        this.description = description;
        this.created_time = created_time;
        this.thing_id = thing_id;
        this.status = status;
        this.exchange_method = exchange_method;
        this.contact = contact;
        this.visible = visible;
        this.deletion = deletion;
    }

    public Post(String description, Date created_time, UUID thing_id, String status, String exchange_method,
            String contact, boolean visible, boolean deletion) {
        this.description = description;
        this.created_time = created_time;
        this.thing_id = thing_id;
        this.status = status;
        this.exchange_method = exchange_method;
        this.contact = contact;
        this.visible = visible;
        this.deletion = deletion;
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

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    public UUID getThing_id() {
        return thing_id;
    }

    public void setThing_id(UUID thing_id) {
        this.thing_id = thing_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExchange_method() {
        return exchange_method;
    }

    public void setExchange_method(String exchange_method) {
        this.exchange_method = exchange_method;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isDeletion() {
        return deletion;
    }

    public void setDeletion(boolean deletion) {
        this.deletion = deletion;
    }

    
}
