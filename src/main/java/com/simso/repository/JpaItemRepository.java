package com.simso.repository;

import com.simso.domain.Item;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaItemRepository implements ItemRepository{

    private final EntityManager em;

    public JpaItemRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Item save(Item item) {
        em.persist(item);
        return item;
    }

    @Override
    public Optional<Item> findByid(Long id) {

        Item item = em.find(Item.class , id);
        return Optional.ofNullable(item);
    }

    @Override
    public Optional<Item> findByname(String name) {

        List<Item> result = em.createQuery("select m from Item m where m.name = :name", Item.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Item> findAll() {
        return em.createQuery("select m from Item m", Item.class).getResultList();
    }

    @Override
    public void delete(Long id) {

        Item item = em.find(Item.class, id);

        em.remove(item);

    }

    @Override
    public Item updateByid(Item item) {

        em.persist(item);

        return item;
    }

}

