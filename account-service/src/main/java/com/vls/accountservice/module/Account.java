package com.vls.accountservice.module;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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

    public Account()
    {
    }
    public Account(String email)
    {
        UUID uuid = UUID.randomUUID();

        this.email = email;
        this.id = uuid;
        this.block  =false;
    }
    public Account(Account account){
        this.id = account.id;
        this.email = account.email;
        this.block = account.block;
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
