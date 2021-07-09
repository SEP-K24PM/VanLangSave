package com.vls.tradeservice.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_account")
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "block")
    private boolean block = false;

    public user()
    {
        UUID uuid = UUID.randomUUID();
        id = uuid;
    }
    public user(String email, String pwd)
    {
        UUID uuid = UUID.randomUUID();

        this.email = email;
        this.id = uuid;
        this.block  =false;
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
    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", name=" + email + ", block=" + block +  "]";
    }
}
