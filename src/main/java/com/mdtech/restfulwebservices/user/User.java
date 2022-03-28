package com.mdtech.restfulwebservices.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class User {

    private int userId;
    @Size(min = 2, message = "Name should contain at least 2 character")
    private String name;
    @Email(message = "Invalid email id")
    private String email;

    public User() {}

    public User(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
