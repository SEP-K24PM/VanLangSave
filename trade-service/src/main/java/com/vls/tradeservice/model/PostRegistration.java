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

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "choosen")
    private  boolean choosen = false ;

    @Column(name = "thing_id", nullable = true)
    private UUID thing_id;

    @Column(name = "user_id")
    private UUID user_id;

    @Column(name = "post_id")
    private  UUID post_id;

    public PostRegistration() {

    }

    public PostRegistration( String description, boolean choosen, UUID thing_id, UUID user_id, UUID post_id) {
        this.description = description;
        this.choosen = choosen;
        this.thing_id = thing_id;
        this.user_id = user_id;
        this.post_id = post_id;
    }

    public PostRegistration(UUID thing_id, UUID user_id, UUID post_id) {
        this.thing_id = thing_id;
        this.user_id = user_id;
        this.post_id = post_id;
    }
    public PostRegistration( UUID user_id, UUID post_id) {
        this.thing_id = null;
        this.user_id = user_id;
        this.post_id = post_id;
        this.description = null;
    }
    public PostRegistration(UUID thing_id, UUID user_id, UUID post_id, String description) {
        this.thing_id = thing_id;
        this.user_id = user_id;
        this.post_id = post_id;
        this.description = description;
    }

    @Override
    public String toString() {
        return "PostRegistration{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", choosen=" + choosen +
                ", thing_id=" + thing_id +
                ", user_id=" + user_id +
                ", post_id=" + post_id +
                '}';
    }
}
