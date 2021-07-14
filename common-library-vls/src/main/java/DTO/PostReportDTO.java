package DTO;

import java.util.UUID;

public class PostReportDTO {
    private UUID id;
    private String description;
    private String reason_by_admin;
    private String handling;
    private UUID post_id;
    private UUID reporter_id;
    private UUID admin_id;
    private PostDTO post;
    private UserAccountDTO reporter;
    private AdminAccountDTO admin;

    public PostReportDTO() {
    }

    public PostReportDTO(String description, String reason_by_admin, String handling, UUID post_id, UUID reporter_id, UUID admin_id) {
        this.description = description;
        this.reason_by_admin = reason_by_admin;
        this.handling = handling;
        this.post_id = post_id;
        this.reporter_id = reporter_id;
        this.admin_id = admin_id;
    }

    public PostReportDTO(UUID id, String description, String reason_by_admin, String handling, UUID post_id, UUID reporter_id, UUID admin_id) {
        this.id = id;
        this.description = description;
        this.reason_by_admin = reason_by_admin;
        this.handling = handling;
        this.post_id = post_id;
        this.reporter_id = reporter_id;
        this.admin_id = admin_id;
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

    public String getReason_by_admin() {
        return reason_by_admin;
    }

    public void setReason_by_admin(String reason_by_admin) {
        this.reason_by_admin = reason_by_admin;
    }

    public String getHandling() {
        return handling;
    }

    public void setHandling(String handling) {
        this.handling = handling;
    }

    public UUID getPost_id() {
        return post_id;
    }

    public void setPost_id(UUID post_id) {
        this.post_id = post_id;
    }

    public UUID getReporter_id() {
        return reporter_id;
    }

    public void setReporter_id(UUID reporter_id) {
        this.reporter_id = reporter_id;
    }

    public UUID getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(UUID admin_id) {
        this.admin_id = admin_id;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }

    public UserAccountDTO getReporter() {
        return reporter;
    }

    public void setReporter(UserAccountDTO reporter) {
        this.reporter = reporter;
    }

    public AdminAccountDTO getAdmin() {
        return admin;
    }

    public void setAdmin(AdminAccountDTO admin) {
        this.admin = admin;
    }
}
