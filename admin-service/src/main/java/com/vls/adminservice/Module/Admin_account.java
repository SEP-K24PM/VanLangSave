package com.vls.adminservice.Module;


import javax.persistence.*;

@Entity
@Table(name = "admin_account")
public class Admin_account {
    private String id;
    @Id
    private String username;
    private String pwd;

}
