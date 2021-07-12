package com.vls.tradeservice.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "post_registration")
public class PostRegistWithEntities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "chosen")
    private  boolean chosen;

    @Column(name = "post_id")
    private  UUID post_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="thing_id")
    private Thing thing;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private UserAccount userAccount;

    public PostRegistWithEntities() {
    }

    public PostRegistWithEntities(UUID id, String description, boolean chosen, UUID post_id) {
        this.id = id;
        this.description = description;
        this.chosen = chosen;
        this.post_id = post_id;
    }

    public PostRegistWithEntities(String description, boolean chosen, UUID post_id) {
        this.description = description;
        this.chosen = chosen;
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

    public UUID getPost_id() {
        return post_id;
    }

    public void setPost_id(UUID post_id) {
        this.post_id = post_id;
    }

    public Thing getThing() {
        return thing;
    }

    public void setThing(Thing thing) {
        this.thing = thing;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
