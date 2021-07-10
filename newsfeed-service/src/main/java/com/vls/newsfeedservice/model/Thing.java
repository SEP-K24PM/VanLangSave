package com.vls.newsfeedservice.model;

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

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "used_time")
    private String used_time;

    @Column(name = "image")
    private String image;

    @Column(name = "user_id")
    private UUID user_id;

    @Column(name = "category_id")
    private UUID category_id;

    @Column(name = "post_id")
    private UUID post_id;

    public Thing(){
    }

    public Thing(String thing_name, String origin,
                 Integer quantity, String used_time,
                 UUID user_id, UUID category_id, UUID post_id) {
        this.thing_name = thing_name;
        this.origin = origin;
        this.quantity = quantity;
        this.used_time = used_time;
        this.user_id = user_id;
        this.category_id = category_id;
        this.post_id = post_id;
    }

    public UUID getId() {
        return id;
    }
    public String getThing_name() {
        return thing_name;
    }
    public String getOrigin() {
        return origin;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public String getUsed_time() {
        return used_time;
    }
    public UUID getUser_id() {
        return user_id;
    }
    public UUID getCategory_id() {
        return category_id;
    }
    public UUID getPost_id() {
        return post_id;
    }


    public void setId(UUID id) {
        this.id = id;
    }

    public void setThing_name(String thing_name) {
        this.thing_name = thing_name;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setUsed_time(String used_time) {
        this.used_time = used_time;
    }

    public void setUser_id(UUID user_id) { this.user_id = user_id; }

    public void setCategory_id(UUID category_id_id) { this.category_id = category_id_id; }

    public void setPost_id(UUID post_id) {
        this.post_id = post_id;
    }


    @Override
    public String toString() {
        return "thing [id=" + id + ", " +
                "thing_name=" + thing_name + ", " +
                "origin=" + origin + ", " +
                "quantity=" + quantity + ", " +
                "used_time=" + used_time + "," +
                "user_id=" + user_id + "," +
                "category_id=" + category_id + "," +
                "post_id=" + post_id + "," + "]";
    }
}
