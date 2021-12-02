package com.simso.repository;

import com.simso.domain.Item;
import com.simso.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    private final EntityManager em;


    public JpaUserRepository(EntityManager em) {
        this.em = em;

    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findByid(Long id) {

        User user = em.find(User.class , id);
        return Optional.ofNullable(user);
    }


    @Override
    public Optional<User> findByname(String name) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User updateByid(User user) {
        return null;
    }

}