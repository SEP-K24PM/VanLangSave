package DTO;

import java.util.UUID;

public class AdminAccountDTO {
    private UUID id;
    private String email;
    private String pwd;
    private String role;

    public AdminAccountDTO() {
    }

    public AdminAccountDTO(String email, String pwd, String role) {
        this.email = email;
        this.pwd = pwd;
        this.role = role;
    }

    public AdminAccountDTO(UUID id, String email, String pwd, String role) {
        this.id = id;
        this.email = email;
        this.pwd = pwd;
        this.role = role;
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
