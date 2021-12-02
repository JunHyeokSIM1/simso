package com.simso.repository;

import com.simso.domain.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    Item save(Item item);

    Optional<Item> findByid(Long id);

    Optional<Item> findByname(String name);

    List<Item> findAll();

    void delete(Long id);

    Item updateByid(Item item);

}
