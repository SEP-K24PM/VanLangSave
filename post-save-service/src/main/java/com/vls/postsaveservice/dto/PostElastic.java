package com.vls.postsaveservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = PostElastic.class)
public class PostElastic {

    private String id;
    private String description;
    private String exchange_methods;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date created_time;
    private String thing_name;
    private String origin;
    private String category_name;

    public PostElastic() {
    }

    public PostElastic(String id, String description, String exchange_methods, Date created_time, String thing_name, String origin, String category_name) {
        this.id = id;
        this.description = description;
        this.exchange_methods = exchange_methods;
        this.created_time = created_time;
        this.thing_name = thing_name;
        this.origin = origin;
        this.category_name = category_name;
    }

    public PostElastic(String description, String exchange_methods, Date created_time, String thing_name, String origin, String category_name) {
        this.description = description;
        this.exchange_methods = exchange_methods;
        this.created_time = created_time;
        this.thing_name = thing_name;
        this.origin = origin;
        this.category_name = category_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    public String getThing_name() {
        return thing_name;
    }

    public void setThing_name(String thing_name) {
        this.thing_name = thing_name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return "PostElastic{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", exchange_methods='" + exchange_methods + '\'' +
                ", created_time='" + created_time + '\'' +
                ", thing_name='" + thing_name + '\'' +
                ", origin='" + origin + '\'' +
                ", category_name='" + category_name + '\'' +
                '}';
    }
}
