package com.vls.newsfeedservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
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

    @Column(name = "exchange_method")
    private String exchange_method;

    @Column(name = "status")
    private String status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="thing_id")
    private Thing thing;

    public Post(){

    }

    public Post(UUID id,String description, Date created_time, boolean visible, boolean deletion,
                String contact, UUID thing_id, String exchange_method, String status) {
        this.id = id;
        this.description = description;
        this.created_time = created_time;
        this.visible = visible;
        this.deletion = deletion;
        this.contact = contact;
        this.exchange_method = exchange_method;
        this.status = status;
    }

    public Post(String description, Date created_time, boolean visible, boolean deletion,
                String contact, UUID thing_id, String exchange_method, String status) {
        this.description = description;
        this.created_time = created_time;
        this.visible = visible;
        this.deletion = deletion;
        this.contact = contact;
        this.exchange_method = exchange_method;
        this.status = status;
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

    public boolean isDeletion() {
        return deletion;
    }

    public void setDeletion(boolean deletion) {
        this.deletion = deletion;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getExchange_method() {
        return exchange_method;
    }

    public void setExchange_method(String exchange_method) {
        this.exchange_method = exchange_method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Thing getThing() {
        return thing;
    }

    public void setThing(Thing thing) {
        this.thing = thing;
    }
}


