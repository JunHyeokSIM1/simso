package com.simso.repository;

import com.simso.domain.Item;
import com.simso.domain.User;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
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
    public Optional<User> findByname(String username) {

        List<User> result = em.createQuery("select m from User m where m.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<User> findAll() {

        return em.createQuery("select m from User m", User.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        User user = em.find(User.class , id);

        em.remove(user);
    }

    @Override
    public User updateByid(User user) {
        return null;
    }

}