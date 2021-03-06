package com.vls.ratingservice.model;

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

    @Column(name = "rated_user_id")
    private UUID rated_user_id;

    @Column(name = "rater_id")
    private UUID rater_id;

    @Column(name = "post_id")
    private UUID post_id;

    public UserRating() {
    }

    public UserRating(UUID id, String description, int rating, UUID rated_user_id, UUID rater_id, UUID post_id) {
        this.id = id;
        this.description = description;
        this.rating = rating;
        this.rated_user_id = rated_user_id;
        this.rater_id = rater_id;
        this.post_id = post_id;
    }

    public UserRating(String description, int rating, UUID rated_user_id, UUID rater_id, UUID post_id) {
        this.description = description;
        this.rating = rating;
        this.rated_user_id = rated_user_id;
        this.rater_id = rater_id;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public UUID getRated_user_id() {
        return rated_user_id;
    }

    public void setRated_user_id(UUID rated_user_id) {
        this.rated_user_id = rated_user_id;
    }

    public UUID getRater_id() {
        return rater_id;
    }

    public void setRater_id(UUID rater_id) {
        this.rater_id = rater_id;
    }

    public UUID getPost_id() {
        return post_id;
    }

    public void setPost_id(UUID post_id) {
        this.post_id = post_id;
    }
}
