package com.simso.repository;

import com.simso.domain.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class JpaItemRepositoryTest {

    @Autowired
    JpaItemRepository jpaItemRepository;

    @Test
    public void save(){

        //given
        Item item = new Item();
        item.setName("testItem");


        //when
        jpaItemRepository.save(item);

        Item result = jpaItemRepository.findByid(item.getId()).get();

        //then
        assertThat(item).isEqualTo(result);
    }

    @Test
    public void findName(){
        Item item = new Item();
        item.setName("testItem2");
        jpaItemRepository.save(item);

        Item item2 = new Item();
        item2.setName("testItem3");
        jpaItemRepository.save(item2);

        Item result = jpaItemRepository.findByname("testItem2").get();

        assertThat(result).isEqualTo(item);


    }

    @Test
    public void findAll(){

        Item item2 = new Item();
        item2.setName("testItem3");
        jpaItemRepository.save(item2);

        List<Item> result = jpaItemRepository.findAll();

        assertThat(result.size()).isEqualTo(1);

        for (Item M : result) {
            System.out.println("=====================");
            System.out.println(M);
        }
    }
    
}