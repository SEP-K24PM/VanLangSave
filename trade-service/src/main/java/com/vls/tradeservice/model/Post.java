package com.vls.tradeservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date created_time;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="thing_id", referencedColumnName="id")
    private Thing thing;

    @Column(name = "status")
    private String status;

    @Column(name = "exchange_method")
    private String exchange_method;

    @Column(name = "contact")
    private String contact;

    @Column(name = "given")
    private UUID given;

    @Column(name = "giver")
    private UUID giver;

    public Post() {
    }

    

    public Post(String description, Date created_time, Thing thing, String status, String exchange_method,
            String contact, UUID given, UUID giver) {
        this.description = description;
        this.created_time = created_time;
        this.thing = thing;
        this.status = status;
        this.exchange_method = exchange_method;
        this.contact = contact;
        this.given = given;
        this.giver = giver;
    }



    public Post(UUID id, String description, Date created_time, Thing thing, String status, String exchange_method,
            String contact, UUID given, UUID giver) {
        this.id = id;
        this.description = description;
        this.created_time = created_time;
        this.thing = thing;
        this.status = status;
        this.exchange_method = exchange_method;
        this.contact = contact;
        this.given = given;
        this.giver = giver;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExchange_method() {
        return exchange_method;
    }

    public void setExchange_method(String exchange_method) {
        this.exchange_method = exchange_method;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public UUID getGiven() {
        return given;
    }



    public void setGiven(UUID given) {
        this.given = given;
    }



    public UUID getGiver() {
        return giver;
    }



    public void setGiver(UUID giver) {
        this.giver = giver;
    }
    
}
