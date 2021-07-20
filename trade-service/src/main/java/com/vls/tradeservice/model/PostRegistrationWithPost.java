package com.vls.tradeservice.model;

import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "post_registration")
public class PostRegistrationWithPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "chosen")
    private  boolean chosen = false ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="thing_id")
    private Thing thing;

    @Column(name = "user_id")
    private UUID user_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="post_id")
    private Post post;

    public PostRegistrationWithPost() {
    }

    public PostRegistrationWithPost(String description, boolean chosen, UUID user_id) {
        this.description = description;
        this.chosen = chosen;
        this.user_id = user_id;
    }

    public PostRegistrationWithPost(UUID id, String description, boolean chosen, UUID user_id) {
        this.id = id;
        this.description = description;
        this.chosen = chosen;
        this.user_id = user_id;
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

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Thing getThing() {
        return thing;
    }

    public void setThing(Thing thing) {
        this.thing = thing;
    }

}
