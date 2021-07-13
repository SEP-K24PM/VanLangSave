package DTO;

import java.util.UUID;

public class UserHandlingDTO {
    private UUID id;
    private String handling;
    private String reason;
    private UUID user_id;
    private UUID admin_id;
    private UserAccountDTO userAccount;
    private AdminAccountDTO adminAccount;

    public UserHandlingDTO() {
    }

    public UserHandlingDTO(String handling, String reason, UUID user_id, UUID admin_id) {
        this.handling = handling;
        this.reason = reason;
        this.user_id = user_id;
        this.admin_id = admin_id;
    }

    public UserHandlingDTO(UUID id, String handling, String reason, UUID user_id, UUID admin_id) {
        this.id = id;
        this.handling = handling;
        this.reason = reason;
        this.user_id = user_id;
        this.admin_id = admin_id;
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
        return userAccount;
    }

    public void setUser(UserAccountDTO userAccount) {
        this.userAccount = userAccount;
    }

    public AdminAccountDTO getAdmin() {
        return adminAccount;
    }

    public void setAdmin(AdminAccountDTO adminAccount) {
        this.adminAccount = adminAccount;
    }
}
