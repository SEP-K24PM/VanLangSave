package DTO;

import java.util.Date;
import java.util.UUID;

public class NotificationDTO {
    private UUID id;
    private String description;
    private String url;
    private Date time;
    private UUID user_id;
    private UserAccountDTO userAccountDTO;

    public NotificationDTO() {
    }

    public NotificationDTO(String description, String url, Date time, UUID user_id) {
        this.description = description;
        this.url = url;
        this.time = time;
        this.user_id = user_id;
    }

    public NotificationDTO(UUID id, String description, String url, Date time, UUID user_id) {
        this.id = id;
        this.description = description;
        this.url = url;
        this.time = time;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public UserAccountDTO getUserAccountDTO() {
        return userAccountDTO;
    }

    public void setUserAccountDTO(UserAccountDTO userAccountDTO) {
        this.userAccountDTO = userAccountDTO;
    }
}
