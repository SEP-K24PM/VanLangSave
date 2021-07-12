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

    @Column(name = "chosen")
    private  boolean chosen = false ;

    @Column(name = "thing_id", nullable = true)
    private UUID thing_id;

    @Column(name = "user_id")
    private UUID user_id;

    @Column(name = "post_id")
    private  UUID post_id;

    public PostRegistration() {

    }


    public PostRegistration(UUID id, String description, boolean chosen, UUID thing_id, UUID user_id, UUID post_id) {
        this.id = id;
        this.description = description;
        this.chosen = chosen;
        this.thing_id = thing_id;
        this.user_id = user_id;
        this.post_id = post_id;
    }

    public PostRegistration(String description, boolean chosen, UUID thing_id, UUID user_id, UUID post_id) {
        this.description = description;
        this.chosen = chosen;
        this.thing_id = thing_id;
        this.user_id = user_id;
        this.post_id = post_id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isChosen() {
        return chosen;
    }

    public void setChosen(boolean choosen) {
        this.chosen = choosen;
    }

    public UUID getThing_id() {
        return thing_id;
    }

    public void setThing_id(UUID thing_id) {
        this.thing_id = thing_id;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public UUID getPost_id() {
        return post_id;
    }

    public void setPost_id(UUID post_id) {
        this.post_id = post_id;
    }

    @Override
    public String toString() {
        return "PostRegistration{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", choosen=" + chosen +
                ", thing_id=" + thing_id +
                ", user_id=" + user_id +
                ", post_id=" + post_id +
                '}';
    }
}
