package DTO;

import java.util.UUID;

public class CategoryDTO {
    private UUID id;
    private String category_name;

    public CategoryDTO() {
    }

    public CategoryDTO(String category_name) {
        this.category_name = category_name;
    }

    public CategoryDTO(UUID id, String category_name) {
        this.id = id;
        this.category_name = category_name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
