package com.vls.postsearchservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "postelastic", createIndex = false)
public class postelastic {
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String description;
    @Field(type = FieldType.Text)
    private String exchange_methods;
    @Field(type = FieldType.Text)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date created_time;
    @Field(type = FieldType.Boolean)
    private boolean visible;
    @Field(type = FieldType.Text)
    private String thing_name;
    @Field(type = FieldType.Text)
    private String origin;
    @Field(type = FieldType.Text)
    private String category_name;

    public postelastic() {
    }

    public postelastic(String id, String description, String exchange_methods, Date created_time, boolean visible, String thing_name, String origin, String category_name) {
        this.id = id;
        this.description = description;
        this.exchange_methods = exchange_methods;
        this.created_time = created_time;
        this.thing_name = thing_name;
        this.origin = origin;
        this.category_name = category_name;
    }

    public postelastic(String description, String exchange_methods, Date created_time, boolean visible, String thing_name, String origin, String category_name) {
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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
        return "postelastic{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", exchange_methods='" + exchange_methods + '\'' +
                ", created_time=" + created_time +
                ", visible=" + visible +
                ", thing_name='" + thing_name + '\'' +
                ", origin='" + origin + '\'' +
                ", category_name='" + category_name + '\'' +
                '}';
    }
}