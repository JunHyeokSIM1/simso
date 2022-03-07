package com.simso.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Builder
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void update(String username, String password){
        this.username = username;
        this.password = password;
    }
}
