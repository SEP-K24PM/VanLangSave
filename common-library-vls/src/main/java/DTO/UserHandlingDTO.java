package DTO;

import java.util.Date;
import java.util.UUID;

public class UserHandlingDTO {
    private UUID id;
    private String reason;
    private UUID user_id;
    private UUID admin_id;
    private Date time;
    private UserAccountDTO userAccount;
    private AdminAccountDTO adminAccount;

    public UserHandlingDTO() {
    }

    public UserHandlingDTO(String reason, UUID user_id, UUID admin_id, Date time) {
        this.reason = reason;
        this.user_id = user_id;
        this.admin_id = admin_id;
        this.time = time;
    }

    public UserHandlingDTO(UUID id, String reason, UUID user_id, UUID admin_id, Date time) {
        this.id = id;
        this.reason = reason;
        this.user_id = user_id;
        this.admin_id = admin_id;
        this.time = time;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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
