package DTO;

import java.util.UUID;

public class UserAccountDTO {
    private UUID id;
    private String email;
    private boolean block;

    public UserAccountDTO() {
    }

    public UserAccountDTO(String email, boolean block) {
        this.email = email;
        this.block = block;
    }

    public UserAccountDTO(UUID id, String email, boolean block) {
        this.id = id;
        this.email = email;
        this.block = block;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }
}
