package com.vls.managementservice.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "admin_account")
public class AdminAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "pwd")
    private String pwd;


    public AdminAccount() {
    }

    public AdminAccount(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }

    public AdminAccount(UUID id, String email, String pwd) {
        this.id = id;
        this.email = email;
        this.pwd = pwd;
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "Admin_Account{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
