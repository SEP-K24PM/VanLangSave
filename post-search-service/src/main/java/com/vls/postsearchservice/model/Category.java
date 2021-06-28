package com.vls.postsearchservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

public class Category {
    @Id
    private UUID id;

    @Field(type = FieldType.Text)
    private String category_name;

    public Category() {
    }

    public Category(String category_name) {
        this.category_name = category_name;
    }

    public Category(UUID id, String category_name) {
        this.id = id;
        this.category_name = category_name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return "CategoryObject{" +
                "id=" + id +
                ", category_name='" + category_name + '\'' +
                '}';
    }
}
