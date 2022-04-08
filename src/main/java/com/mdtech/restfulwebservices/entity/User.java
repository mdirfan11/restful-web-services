package com.mdtech.restfulwebservices.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TBL_USER")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Size(min = 2, message = "Name should have at least two character")
    @Column(name = "U_NAME")
    private String username;
    @Email
    @Column(name = "U_EMAIL")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
