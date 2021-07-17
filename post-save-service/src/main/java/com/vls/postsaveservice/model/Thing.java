package com.vls.postsaveservice.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "thing")
public class Thing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "thing_name")
    private String thing_name;

    @Column(name = "origin")
    private String origin;

    @Column(name = "price")
    private int price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "used_time")
    private String used_time;

    @Column(name = "image")
    private String image;

    @Column(name = "user_id")
    private UUID user_id;

    @Column(name = "category_id")
    private UUID category_id;


    public Thing() {
    }

    public Thing(UUID id, String thing_name, String origin, int price, int quantity, String used_time,
                 String image, UUID user_id, UUID category_id) {
        this.id = id;
        this.thing_name = thing_name;
        this.origin = origin;
        this.price = price;
        this.quantity = quantity;
        this.used_time = used_time;
        this.image = image;
        this.user_id = user_id;
        this.category_id = category_id;
    }

    public Thing(String thing_name, String origin, int price, int quantity, String used_time,
                 String image, UUID user_id, UUID category_id) {
        this.thing_name = thing_name;
        this.origin = origin;
        this.price = price;
        this.quantity = quantity;
        this.used_time = used_time;
        this.image = image;
        this.user_id = user_id;
        this.category_id = category_id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUsed_time() {
        return used_time;
    }

    public void setUsed_time(String used_time) {
        this.used_time = used_time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public UUID getCategory_id() {
        return category_id;
    }

    public void setCategory_id(UUID category_id) {
        this.category_id = category_id;
    }

}
