package com.vls.postduplicationservice.domainobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Component
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = PostObject.class)
public class PostObject implements Serializable {
    private UUID id;
    private String description;
    private String exchange_methods;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date created_time;
    private ThingObject thingObject;

    public PostObject() {
    }

    public PostObject(UUID id, String description, String exchange_methods, Date created_time, ThingObject thingObject) {
        this.id = id;
        this.description = description;
        this.exchange_methods = exchange_methods;
        this.created_time = created_time;
        this.thingObject = thingObject;
    }

    public PostObject(String description, String exchange_methods, Date created_time, ThingObject thingObject) {
        this.description = description;
        this.exchange_methods = exchange_methods;
        this.created_time = created_time;
        this.thingObject = thingObject;
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

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    public ThingObject getThing() {
        return thingObject;
    }

    public void setThing(ThingObject thingObject) {
        this.thingObject = thingObject;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", exchange_methods='" + exchange_methods + '\'' +
                ", created_time=" + created_time +
                ", thing=" + thingObject +
                '}';
    }
}
