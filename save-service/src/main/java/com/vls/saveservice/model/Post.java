package com.vls.saveservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.UUID;

public class Post {
    private UUID id;
    private String description;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date created_time;
    private UUID thing_id;
    private UUID post_status_id;
    private String exchange_method;

    public Post() {
    }

    public Post(String description, Date created_time, UUID thing_id, UUID post_status_id, String exchange_method) {
        this.description = description;
        this.created_time = created_time;
        this.thing_id = thing_id;
        this.post_status_id = post_status_id;
        this.exchange_method = exchange_method;
    }

    public Post(UUID id, String description, Date created_time, UUID thing_id, UUID post_status_id, String exchange_method) {
        this.id = id;
        this.description = description;
        this.created_time = created_time;
        this.thing_id = thing_id;
        this.post_status_id = post_status_id;
        this.exchange_method = exchange_method;
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

    public UUID getPost_status_id() {
        return post_status_id;
    }

    public void setPost_status_id(UUID post_status_id) {
        this.post_status_id = post_status_id;
    }

    public String getExchange_method() {
        return exchange_method;
    }

    public void setExchange_method(String exchange_method) {
        this.exchange_method = exchange_method;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", created_time=" + created_time +
                ", thing_id=" + thing_id +
                ", post_status_id=" + post_status_id +
                ", exchange_method='" + exchange_method + '\'' +
                '}';
    }
}
