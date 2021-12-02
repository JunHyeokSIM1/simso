package com.simso.domain;

import javax.persistence.*;

@Entity
public class User {

    @Id   @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "USER_NAME")
    private String username;

    @Column(name = "password")
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
