package com.simso.service;

import com.simso.domain.Item;
import com.simso.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest

class ItemServiceTest {

    @Autowired ItemService itemService;
    @Autowired
    ItemRepository itemRepository;

    @Test
    public void 등록(){
        //give
        Item item = new Item();
        item.setName("testResister2");

        //when
        Long saveId = itemService.register(item);

        //then
        Item findItem = itemService.findOne(saveId).get();
        assertThat(item.getName()).isEqualTo(findItem.getName());
    }




}