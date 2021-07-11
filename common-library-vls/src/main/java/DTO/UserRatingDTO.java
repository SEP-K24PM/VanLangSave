package DTO;

import java.util.UUID;

public class UserRatingDTO {
    private UUID id;
    private String description;
    private int rating;
    private UUID rated_user_id;
    private UUID rater_id;
    private UUID post_id;

    private UserAccountDTO rated_user;
    private UserAccountDTO rater;
    private PostDTO post;

    public UserRatingDTO() {
    }

    public UserRatingDTO(String description, int rating, UUID rated_user_id, UUID rater_id, UUID post_id, UserAccountDTO rated_user, UserAccountDTO rater, PostDTO post) {
        this.description = description;
        this.rating = rating;
        this.rated_user_id = rated_user_id;
        this.rater_id = rater_id;
        this.post_id = post_id;
        this.rated_user = rated_user;
        this.rater = rater;
        this.post = post;
    }

    public UserRatingDTO(UUID id, String description, int rating, UUID rated_user_id, UUID rater_id, UUID post_id, UserAccountDTO rated_user, UserAccountDTO rater, PostDTO post) {
        this.id = id;
        this.description = description;
        this.rating = rating;
        this.rated_user_id = rated_user_id;
        this.rater_id = rater_id;
        this.post_id = post_id;
        this.rated_user = rated_user;
        this.rater = rater;
        this.post = post;
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

    public UserAccountDTO getRated_user() {
        return rated_user;
    }

    public void setRated_user(UserAccountDTO rated_user) {
        this.rated_user = rated_user;
    }

    public UserAccountDTO getRater() {
        return rater;
    }

    public void setRater(UserAccountDTO rater) {
        this.rater = rater;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }
}
