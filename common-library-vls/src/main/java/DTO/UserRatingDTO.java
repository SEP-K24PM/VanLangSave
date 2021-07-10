package DTO;

import java.util.UUID;

public class UserRatingDTO {
    private UUID id;
    private String description;
    private int rating;
    private UUID rated_user_id;
    private UUID rater_id;
    private UUID post_id;

    public UserRatingDTO() {
    }

    public UserRatingDTO(UUID id, String description, int rating, UUID rated_user_id, UUID rater_id, UUID post_id) {
        this.id = id;
        this.description = description;
        this.rating = rating;
        this.rated_user_id = rated_user_id;
        this.rater_id = rater_id;
        this.post_id = post_id;
    }

    public UserRatingDTO(String description, int rating, UUID rated_user_id, UUID rater_id, UUID post_id) {
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
