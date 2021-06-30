package com.vls.adminservice.Module;


import javax.persistence.*;

@Entity
@Table(name = "admin_account")
public class Admin_account {
    private String id;
    @Id
    private String username;
    private String pwd;
    public Admin_account() {

    }
    public Admin_account (Admin_account info){
        this.id = info.id;
        this.username = info.username;
        this.pwd = info.pwd;
    }
    public String getUUID(){
        return id;
    }
    @Override
    public String toString(){
        String result= "username:"+this.username+" pwd"+this.pwd + " uuid" + this.id;
        return result;
    }

}
