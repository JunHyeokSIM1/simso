package com.simso.repository;

import com.simso.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryOld {
    User save(User user);

    Optional<User> findByid(Long id);

    Optional<User> findByname(String name);

    List<User> findAll();

    void delete(Long id);

    User updateByid(User user);

}
