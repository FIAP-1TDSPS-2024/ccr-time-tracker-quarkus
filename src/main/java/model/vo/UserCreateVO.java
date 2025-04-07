package model.vo;

import model.entity.UserEntity;

public class UserCreateVO {
    private String name;
    private String email;
    private String password;

    public UserCreateVO() {
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

    public UserEntity toEntity() {
        return new UserEntity(null, name, email, password);
    }
}