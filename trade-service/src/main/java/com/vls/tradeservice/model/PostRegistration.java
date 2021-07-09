package com.vls.tradeservice.model;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "post_registration")
public class PostRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "choosen")
    private  boolean choosen;

    @Column(name = "thing_id")
    private UUID thing_id;

    @Column(name = "user_id")
    private UUID user_id;

    @Column(name = "ipost_idd")
    private  UUID post_id;


}
