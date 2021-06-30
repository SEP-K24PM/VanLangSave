package com.vls.managerservice.module;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "manager_account")
public class Manager_module {
    @Id
    private String id;
    private String username;
    private String pwd;
    public Manager_module() {

    }
    public Manager_module (Manager_module info){
        this.id = info.id;
        this.username = info.username;
        this.pwd = info.pwd;
    }
    public String getUUID(){
        return id;
    }
}
