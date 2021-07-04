package com.vls.thingservice.model;

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
    private UUID userid;

    @Column(name = "category_id")
    private UUID category_id;

    @Column(name = "post_id")
    private UUID post_id;

    private String category_name;

    public Thing() {
    }

    public Thing(String thing_name, String origin, int price, int quantity, String used_time, String image, UUID userid, UUID category_id, UUID post_id) {
        this.thing_name = thing_name;
        this.origin = origin;
        this.price = price;
        this.quantity = quantity;
        this.used_time = used_time;
        this.image = image;
        this.userid = userid;
        this.category_id = category_id;
        this.post_id = post_id;
    }

    public Thing(UUID id, String thing_name, String origin, int price, int quantity, String used_time, String image, UUID userid, UUID category_id, UUID post_id) {
        this.id = id;
        this.thing_name = thing_name;
        this.origin = origin;
        this.price = price;
        this.quantity = quantity;
        this.used_time = used_time;
        this.image = image;
        this.userid = userid;
        this.category_id = category_id;
        this.post_id = post_id;
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

    public UUID getUserid() {
        return userid;
    }

    public void setUserid(UUID user_id) {
        this.userid = user_id;
    }

    public UUID getCategory_id() {
        return category_id;
    }

    public void setCategory_id(UUID category_id) {
        this.category_id = category_id;
    }

    public UUID getPost_id() {
        return post_id;
    }

    public void setPost_id(UUID post_id) {
        this.post_id = post_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return "Thing{" +
                "id=" + id +
                ", thing_name='" + thing_name + '\'' +
                ", origin='" + origin + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", used_time='" + used_time + '\'' +
                ", image='" + image + '\'' +
                ", user_id=" + userid +
                ", category_id=" + category_id +
                ", post_id=" + post_id +
                '}';
    }
}
