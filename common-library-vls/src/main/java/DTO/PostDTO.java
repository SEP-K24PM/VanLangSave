package DTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class PostDTO {
    private UUID id;
    private String description;
    private Date created_time;
    private boolean visible;
    private boolean deletion;
    private String contact;
    private String exchange_method;
    private String status;
    private UUID giver;
    private UUID given;
    private UUID thing_id;
    private ThingDTO thing;
    private List<PostRegistrationDTO> postRegistrationList;

    public PostDTO() {
    }

    

    public PostDTO(String description, Date created_time, boolean visible, boolean deletion, String contact,
            String exchange_method, String status, UUID giver, UUID given, UUID thing_id) {
        this.description = description;
        this.created_time = created_time;
        this.visible = visible;
        this.deletion = deletion;
        this.contact = contact;
        this.exchange_method = exchange_method;
        this.status = status;
        this.giver = giver;
        this.given = given;
        this.thing_id = thing_id;
    }



    public PostDTO(UUID id, String description, Date created_time, boolean visible, boolean deletion, String contact,
            String exchange_method, String status, UUID giver, UUID given, UUID thing_id) {
        this.id = id;
        this.description = description;
        this.created_time = created_time;
        this.visible = visible;
        this.deletion = deletion;
        this.contact = contact;
        this.exchange_method = exchange_method;
        this.status = status;
        this.giver = giver;
        this.given = given;
        this.thing_id = thing_id;
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

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isDeletion() {
        return deletion;
    }

    public void setDeletion(boolean deletion) {
        this.deletion = deletion;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getExchange_method() {
        return exchange_method;
    }

    public void setExchange_method(String exchange_method) {
        this.exchange_method = exchange_method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getThing_id() {
        return thing_id;
    }

    public void setThing_id(UUID thing_id) {
        this.thing_id = thing_id;
    }

    public ThingDTO getThing() {
        return thing;
    }

    public void setThing(ThingDTO thing) {
        this.thing = thing;
    }

    public UUID getGiver() {
        return giver;
    }

    public void setGiver(UUID giver) {
        this.giver = giver;
    }

    public UUID getGiven() {
        return given;
    }

    public void setGiven(UUID given) {
        this.given = given;
    }

    public List<PostRegistrationDTO> getPostRegistrationList() {
        return postRegistrationList;
    }

    public void setPostRegistrationList(List<PostRegistrationDTO> postRegistrationList) {
        this.postRegistrationList = postRegistrationList;
    }
}
