package DTO;

import java.util.List;
import java.util.UUID;

public class UserAccountDTO {
    private UUID id;
    private String email;
    private boolean block;

    private List<UserRatingDTO> userRatingList;
    private List<ThingDTO> thingList;
    private List<NotificationDTO> notificationList;
    private List<UserHandlingDTO> userHandlingList;
    private List<PostDTO> postList;

    public UserAccountDTO() {
    }

    public UserAccountDTO(String email, boolean block) {
        this.email = email;
        this.block = block;
    }

    public UserAccountDTO(UUID id, String email, boolean block) {
        this.id = id;
        this.email = email;
        this.block = block;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public List<UserRatingDTO> getUserRatingList() {
        return userRatingList;
    }

    public void setUserRatingList(List<UserRatingDTO> userRatingList) {
        this.userRatingList = userRatingList;
    }

    public List<ThingDTO> getThingList() {
        return thingList;
    }

    public void setThingList(List<ThingDTO> thingList) {
        this.thingList = thingList;
    }

    public List<NotificationDTO> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<NotificationDTO> notificationList) {
        this.notificationList = notificationList;
    }

    public List<UserHandlingDTO> getUserHandlingList() {
        return userHandlingList;
    }

    public void setUserHandlingList(List<UserHandlingDTO> userHandlingList) {
        this.userHandlingList = userHandlingList;
    }

    public List<PostDTO> getPostList() {
        return postList;
    }

    public void setPostList(List<PostDTO> postList) {
        this.postList = postList;
    }
}
