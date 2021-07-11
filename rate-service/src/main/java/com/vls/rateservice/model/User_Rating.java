package com.vls.rateservice.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_rating")
public class User_Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @Column(name = "description")
    private String description;
    @Column(name = "rating")
    private int rating;
    @Column(name = "rated_user_id")
    private UUID ratedUserId;
    @Column(name = "rater_id")
    private UUID rater_id;
    @Column(name = "post_id")
    private UUID post_id;

    public User_Rating() {
    }

    public User_Rating(UUID id, String description, int rating, UUID ratedUserId, UUID rater_id, UUID post_id) {
        this.id = id;
        this.description = description;
        this.rating = rating;
        this.ratedUserId = ratedUserId;
        this.rater_id = rater_id;
        this.post_id = post_id;
    }

    public User_Rating(String description, int rating, UUID ratedUserId, UUID rater_id, UUID post_id) {
        this.description = description;
        this.rating = rating;
        this.ratedUserId = ratedUserId;
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

    public UUID getRatedUserId() {
        return ratedUserId;
    }

    public void setRatedUserId(UUID rated_user_id) {
        this.ratedUserId = rated_user_id;
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

    @Override
    public String toString() {
        return "User_Rating{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", rated_user_id=" + ratedUserId +
                ", rater_id=" + rater_id +
                ", post_id=" + post_id +
                '}';
    }
}
