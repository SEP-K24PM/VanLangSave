package DTO;

import java.util.UUID;

public class UserHandlingDTO {
    private UUID id;
    private String handling;
    private String reason;
    private UUID user_id;
    private UUID admin_id;

    private UserAccountDTO user;
    private AdminAccountDTO admin;

    public UserHandlingDTO() {
    }

    public UserHandlingDTO(String handling, String reason, UUID user_id, UUID admin_id, UserAccountDTO user, AdminAccountDTO admin) {
        this.handling = handling;
        this.reason = reason;
        this.user_id = user_id;
        this.admin_id = admin_id;
        this.user = user;
        this.admin = admin;
    }

    public UserHandlingDTO(UUID id, String handling, String reason, UUID user_id, UUID admin_id, UserAccountDTO user, AdminAccountDTO admin) {
        this.id = id;
        this.handling = handling;
        this.reason = reason;
        this.user_id = user_id;
        this.admin_id = admin_id;
        this.user = user;
        this.admin = admin;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getHandling() {
        return handling;
    }

    public void setHandling(String handling) {
        this.handling = handling;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public UUID getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(UUID admin_id) {
        this.admin_id = admin_id;
    }

    public UserAccountDTO getUser() {
        return user;
    }

    public void setUser(UserAccountDTO user) {
        this.user = user;
    }

    public AdminAccountDTO getAdmin() {
        return admin;
    }

    public void setAdmin(AdminAccountDTO admin) {
        this.admin = admin;
    }
}
