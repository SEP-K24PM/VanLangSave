package com.vls.managerservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.UUID;

public class Category {
    private UUID id;
    private String category_name;

    public Category() {
    }

    public Category(UUID id, String category_name) {
        this.id = id;
        this.category_name = category_name;
    }

    public Category(String category_name) {
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
        return "Category{" +
                "id=" + id +
                ", category_name='" + category_name + '\'' +
                '}';
    }
}
