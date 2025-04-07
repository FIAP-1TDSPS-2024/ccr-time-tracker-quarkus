package model.vo;

import model.entity.UserEntity;

public class UserUpdateVO {
    private String name;
    private String email;
    private String password;

    public UserUpdateVO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserEntity toEntity(Long id) {
        return new UserEntity(id, name, email, password);
    }
}