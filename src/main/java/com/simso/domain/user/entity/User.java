package com.simso.domain.user.entity;

import com.simso.domain.ability.entity.Ability;
import com.simso.domain.roadmap.entity.Roadmap;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "picture")
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @NotNull
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Roadmap> roadmapList = new ArrayList<>();

    @OneToMany(mappedBy = "userAbility", cascade = CascadeType.ALL)
    private List<Ability> abilities;

    @Builder
    public User(Long id,
                String username,
                String password,
                String email,
                String picture,
                Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.abilities = new ArrayList<>();
    }

    public User update(String username, String picture) {
        this.username = username;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
