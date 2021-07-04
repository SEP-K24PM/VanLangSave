package com.vls.thingservice.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "category_name")
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
        return "Category{" +
                "id=" + id +
                ", category_name='" + category_name + '\'' +
                '}';
    }
}
