package com.vls.reportservice.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "post_report")
public class PostReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "reason_by_admin")
    private String reason_by_admin;

    @Column(name = "handling")
    private String handling;

    @Column(name = "post_id")
    private UUID post_id;

    @Column(name = "reporter_id")
    private UUID reporter_id;

    @Column(name = "admin_id")
    private UUID admin_id;

    public PostReport() {
    }

    public PostReport(String description, UUID reporter_id, UUID admin_id) {
        this.description = description;
        this.reporter_id = reporter_id;
        this.admin_id = admin_id;
    }

    public PostReport(UUID id, String description, UUID post_id, UUID reporter_id) {
        this.id = id;
        this.description = description;
        this.post_id = post_id;
        this.reporter_id = reporter_id;
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
}
