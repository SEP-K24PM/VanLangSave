package DTO;

import java.util.UUID;

public class PostRegistrationDTO {
    private UUID id;
    private String description;
    private boolean chosen;
    private UUID thing_id;
    private UUID user_id;
    private UUID post_id;
    private ThingDTO thing;
    private UserAccountDTO user;
    private PostDTO post;

    public PostRegistrationDTO() {
    }

    public PostRegistrationDTO(UUID id, String description, boolean chosen, UUID thing_id, UUID user_id, UUID post_id) {
        this.id = id;
        this.description = description;
        this.chosen = chosen;
        this.thing_id = thing_id;
        this.user_id = user_id;
        this.post_id = post_id;
    }

    public PostRegistrationDTO(String description, boolean chosen, UUID thing_id, UUID user_id, UUID post_id) {
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

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
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

    public ThingDTO getThing() {
        return thing;
    }

    public void setThing(ThingDTO thing) {
        this.thing = thing;
    }

    public UserAccountDTO getUser() {
        return user;
    }

    public void setUser(UserAccountDTO user) {
        this.user = user;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }
}
