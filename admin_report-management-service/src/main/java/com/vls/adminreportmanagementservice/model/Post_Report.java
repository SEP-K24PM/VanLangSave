package com.vls.adminreportmanagementservice.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "post_report")
public class Post_Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="reporter_id")
    private User_Account reporter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="admin_id")
    private Admin_Account admin;

    @Column(name = "handling")
    private String handling;

    @Column(name = "reason_by_admin")
    private String reason_by_admin;

    public Post_Report() {
    }

    public Post_Report(String description, String handling, String reason_by_admin) {
        this.description = description;
        this.handling = handling;
        this.reason_by_admin = reason_by_admin;
    }

    public Post_Report(UUID id, String description, String handling, String reason_by_admin) {
        this.id = id;
        this.description = description;
        this.handling = handling;
        this.reason_by_admin = reason_by_admin;
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

    public String getHandling() {
        return handling;
    }

    public void setHandling(String handling) {
        this.handling = handling;
    }

    public String getReason_by_admin() {
        return reason_by_admin;
    }

    public void setReason_by_admin(String reason_by_admin) {
        this.reason_by_admin = reason_by_admin;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User_Account getReporter() {
        return reporter;
    }

    public void setReporter(User_Account reporter) {
        this.reporter = reporter;
    }

    public Admin_Account getAdmin() {
        return admin;
    }

    public void setAdmin(Admin_Account admin) {
        this.admin = admin;
    }
}
