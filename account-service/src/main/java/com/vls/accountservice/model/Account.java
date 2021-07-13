package com.vls.accountservice.model;

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

    public Account()
    {
    }

    public Account(String email)
    {
        this.email = email;
        this.block  =false;
    }
    public Account(Account account){
        this.id = account.id;
        this.email = account.email;
        this.block = account.block;
    }
    public Account(String email, UUID id,boolean block)
    {
        this.email = email;
        this.id = id;
        this.block =block;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }
    public UUID getId()
    {
        return id;
    }
    public boolean isBlock()
    {
        return block;
    }
    public void setEmail(String user_name)
    {
        this.email = user_name;
    }
    public void setBlock(boolean block)
    {
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
