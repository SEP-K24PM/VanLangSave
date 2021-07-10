package com.vls.newsfeedservice.model;


import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "created_time")
    private Date created_time;

    @Column(name = "visible")
    private boolean visible;

    @Column(name = "deletion")
    private boolean deletion;

    @Column(name = "contact")
    private String contact;

    @Column(name = "thing_id")
    private UUID thing_id;

    @Column(name = "post_status_id")
    private UUID post_status_id;

    @Column(name = "exchange_method")
    private String exchange_method;

    public Post(){

    }
    public Post(String description, Date created_time, boolean visible, boolean deletion, String contact, UUID thing_id, UUID post_status_id, String exchange_method) {
        this.description = description;
        this.created_time = created_time;
        this.visible = visible;
        this.deletion = deletion;
        this.contact = contact;
        this.thing_id = thing_id;
        this.post_status_id = post_status_id;
        this.exchange_method = exchange_method;

    }

    public UUID getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public Date getCreated_time() {
        return created_time;
    }
    public boolean getVisible() {
        return visible;
    }
    public boolean getDeletion() {
        return deletion;
    }
    public String getContact() {
        return contact;
    }
    public UUID getThing_id() {
        return thing_id;
    }
    public UUID getPost_status_id() {
        return post_status_id;
    }
    public String getExchange_method() {
        return exchange_method;
    }


    public void setId(UUID id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setDeletion(boolean deletion) {
        this.deletion = deletion;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setThing_id(UUID thing_id) {
        this.thing_id = thing_id;
    }

    public void setPost_status_id(UUID post_status_id) {
        this.post_status_id = post_status_id;
    }

    public void setExchange_method(String exchange_method) {
        this.exchange_method = exchange_method;
    }


    @Override
    public String toString() {
        return "post [id=" + id + ", " +
                "description=" + description + ", " +
                "created_time=" + created_time + ", " +
                "visible=" + visible + ", " +
                "deletion=" + deletion + "," +
                "contact=" + contact + "," +
                "thing_id" + thing_id + "," +
                "post_status_id" + post_status_id + "," +
                "exchange_method" + exchange_method + "]";
    }
}


