package com.vls.managementservice.model;

import com.vls.managementservice.model.Thing;
import com.vls.managementservice.model.UserRating;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "user_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "block")
    private boolean block = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Thing> things;

    @OneToMany(mappedBy = "rated_user", cascade = CascadeType.ALL)
    private List<UserRating> ratings;

    public Account() {
    }

    public Account(UUID id, String email, boolean block) {
        this.id = id;
        this.email = email;
        this.block = block;
    }

    public Account(String email, boolean block) {
        this.email = email;
        this.block = block;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public List<Thing> getThings() {
        return things;
    }

    public void setThings(List<Thing> things) {
        this.things = things;
    }

    public List<UserRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<UserRating> ratings) {
        this.ratings = ratings;
    }
}
