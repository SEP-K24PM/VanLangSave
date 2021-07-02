package com.vls.searchservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.UUID;

public class Post {

    private UUID id;
    private String description;
    private String exchange_methods;
    private String created_time;
    private Thing thing;

    public Post() {
    }

    public Post(UUID id, String description, String exchange_methods, String created_time, Thing thing) {
        this.id = id;
        this.description = description;
        this.exchange_methods = exchange_methods;
        this.created_time = created_time;
        this.thing = thing;
    }

    public Post(String description, String exchange_methods, String created_time, Thing thing) {
        this.description = description;
        this.exchange_methods = exchange_methods;
        this.created_time = created_time;
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

    public String getExchange_methods() {
        return exchange_methods;
    }

    public void setExchange_methods(String exchange_methods) {
        this.exchange_methods = exchange_methods;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public Thing getThing() {
        return thing;
    }

    public void setThing(Thing thing) {
        this.thing = thing;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", exchange_methods='" + exchange_methods + '\'' +
                ", created_time=" + created_time +
                ", thing=" + thing +
                '}';
    }
}
