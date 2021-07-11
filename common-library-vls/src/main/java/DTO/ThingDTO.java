package DTO;

import java.util.UUID;

public class ThingDTO {
    private UUID id;
    private String thing_name;
    private String origin;
    private int price;
    private int quantity;
    private String used_time;
    private String image;
    private UUID user_id;
    private UUID category_id;
    private UUID post_id;
    private UserAccountDTO user;
    private CategoryDTO category;
    private PostDTO post;

    public ThingDTO() {
    }

    public ThingDTO(String thing_name, String origin, int price, int quantity, String used_time, String image, UUID user_id, UUID category_id, UUID post_id) {
        this.thing_name = thing_name;
        this.origin = origin;
        this.price = price;
        this.quantity = quantity;
        this.used_time = used_time;
        this.image = image;
        this.user_id = user_id;
        this.category_id = category_id;
        this.post_id = post_id;
    }

    public ThingDTO(UUID id, String thing_name, String origin, int price, int quantity, String used_time, String image, UUID user_id, UUID category_id, UUID post_id) {
        this.id = id;
        this.thing_name = thing_name;
        this.origin = origin;
        this.price = price;
        this.quantity = quantity;
        this.used_time = used_time;
        this.image = image;
        this.user_id = user_id;
        this.category_id = category_id;
        this.post_id = post_id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getThing_name() {
        return thing_name;
    }

    public void setThing_name(String thing_name) {
        this.thing_name = thing_name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUsed_time() {
        return used_time;
    }

    public void setUsed_time(String used_time) {
        this.used_time = used_time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public UUID getCategory_id() {
        return category_id;
    }

    public void setCategory_id(UUID category_id) {
        this.category_id = category_id;
    }

    public UUID getPost_id() {
        return post_id;
    }

    public void setPost_id(UUID post_id) {
        this.post_id = post_id;
    }

    public UserAccountDTO getUser() {
        return user;
    }

    public void setUser(UserAccountDTO user) {
        this.user = user;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }
}
