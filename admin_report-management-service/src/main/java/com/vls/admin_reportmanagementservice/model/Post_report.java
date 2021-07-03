package com.vls.admin_reportmanagementservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "post_report")
public class Post_report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "post_id")
    private UUID post_id;

    @Column(name = "reporter_id")
    private UUID reporter_id;

    @Column(name = "admin_id")
    private UUID admin_id;

    @Column(name = "handling_id")
    private UUID handling_id;

    @Column(name = "reason_by_admin")
    private String reason_by_admin;

    public Post_report() {
    }

    public Post_report(String description, UUID post_id, UUID reporter_id, UUID admin_id, UUID handling_id, String reason_by_admin) {
        this.description = description;
        this.post_id = post_id;
        this.reporter_id = reporter_id;
        this.admin_id = admin_id;
        this.handling_id = handling_id;
        this.reason_by_admin = reason_by_admin;
    }

    public Post_report(UUID id, String description, UUID post_id, UUID reporter_id, UUID admin_id, UUID handling_id, String reason_by_admin) {
        this.id = id;
        this.description = description;
        this.post_id = post_id;
        this.reporter_id = reporter_id;
        this.admin_id = admin_id;
        this.handling_id = handling_id;
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

    public Date getPost_id() {
        return post_id;
    }

    public void setPost_id(UUID post_id) {
        this.post_id = post_id;
    }

    public UUID getReporter_id_id() {
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

    public UUID getHandling_id() {
        return handling_id;
    }

    public void setHandling_id(UUID handling_id) {
        this.handling_id = handling_id;
    }

    public String getReason_by_admin() {
        return reason_by_admin;
    }

    public void setReason_by_admin(String reason_by_admin) {
        this.reason_by_admin = reason_by_admin;
    }

    @Override
    public String toString() {
        return "post_report{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", post_id=" + post_id +
                ", reporter_id=" + reporter_id +
                ", admin_id=" + admin_id +
                ", handling_id=" + handling_id +
                ", reason_by_admin='" + reason_by_admin + '\'' +
                '}';
    }
}
