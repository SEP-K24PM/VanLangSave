package com.vls.accountservice.module;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "user_account")
public class Account {
    @Id
    @Column(name = "user_name")
    private String user_name;

    @org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
    @Column(name = "id")
    private UUID id;

    @Column(name = "pwd")
    private String pwd;
    @Column(name = "block")
    private boolean block = false;
    @Column(name = "verify")
    private boolean verify = true;

    public Account()
    {
        UUID uuid = UUID.randomUUID();
        id = uuid;
    }
    public Account(String user_name, String pwd)
    {
        UUID uuid = UUID.randomUUID();

        this.user_name = user_name;
        this.id = uuid;
        this.pwd = pwd;
        this.block  =false;
        this.verify = true;
    }
    public String getUser_name()
    {
        return user_name;
    }
    public UUID getId()
    {
        return id;
    }
    public String getPwd()
    {
        return pwd;
    }
    public boolean isBlock()
    {
        return block;
    }
    public boolean isVerify()
    {
        return verify;
    }
    public void setUser_name(String user_name)
    {
        this.user_name = user_name;
    }
    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }
    public void setBlock(boolean block)
    {
        this.block = block;
    }
    public void setVerify(boolean verify)
    {
        this.verify = verify;
    }
    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", name=" + user_name + ", pwd=" + pwd + ", block=" + block + ", verify=" + verify + "]";
    }
}
