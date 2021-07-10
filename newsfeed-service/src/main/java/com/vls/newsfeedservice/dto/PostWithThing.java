package com.vls.newsfeedservice.dto;

import com.vls.newsfeedservice.model.Thing;

import javax.persistence.Column;
import java.util.Date;
import java.util.UUID;

public class PostWithThing {
    private UUID id;
    private String description;
    private Date created_time;
    private boolean visible;
    private boolean deletion;
    private String contact;
    private UUID thing_id;
    private String exchange_method;
    private String status;
    private Thing thing;

    public PostWithThing() {
    }

    public PostWithThing(UUID id, String description, Date created_time, boolean visible,
                         boolean deletion, String contact, UUID thing_id, String exchange_method,
                         String status, Thing thing) {
        this.id = id;
        this.description = description;
        this.created_time = created_time;
        this.visible = visible;
        this.deletion = deletion;
        this.contact = contact;
        this.thing_id = thing_id;
        this.exchange_method = exchange_method;
        this.status = status;
        this.thing = thing;
    }

    public PostWithThing(String description, Date created_time, boolean visible, boolean deletion, String contact, UUID thing_id, String exchange_method, String status, Thing thing) {
        this.description = description;
        this.created_time = created_time;
        this.visible = visible;
        this.deletion = deletion;
        this.contact = contact;
        this.thing_id = thing_id;
        this.exchange_method = exchange_method;
        this.status = status;
        this.thing = thing;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public UUID getThing_id() {
        return thing_id;
    }

    public void setThing_id(UUID thing_id) {
        this.thing_id = thing_id;
    }

    public String getExchange_method() {
        return exchange_method;
    }

    public void setExchange_method(String exchange_method) {
        this.exchange_method = exchange_method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Thing getThing() {
        return thing;
    }

    public void setThing(Thing thing) {
        this.thing = thing;
    }
}
