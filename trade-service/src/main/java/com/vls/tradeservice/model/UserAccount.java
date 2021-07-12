package com.vls.tradeservice.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "block")
    private boolean block;

    public UserAccount() {
    }

    public UserAccount(String email, boolean block) {
        this.email = email;
        this.block = block;
    }

    public UserAccount(UUID id, String email, boolean block) {
        this.id = id;
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
}