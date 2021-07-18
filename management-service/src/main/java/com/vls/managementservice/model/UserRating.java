package com.vls.managementservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_rating")
public class UserRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private int rating;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "rated_user_id")
    private Account rated_user;

    @ManyToOne
    @JoinColumn(name = "rater_id")
    private Account rater;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public UserRating() {
    }

    public UserRating(UUID id, String description, int rating) {
        this.id = id;
        this.description = description;
        this.rating = rating;
    }

    public UserRating(String description, int rating) {
        this.description = description;
        this.rating = rating;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Account getRated_user() {
        return rated_user;
    }

    public void setRated_user(Account rated_user) {
        this.rated_user = rated_user;
    }

    public Account getRater() {
        return rater;
    }

    public void setRater(Account rater) {
        this.rater = rater;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
