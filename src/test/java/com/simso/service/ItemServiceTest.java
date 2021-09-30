package com.simso.service;

import com.simso.domain.Item;
import com.simso.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    @Transactional
    public void 중복_예외_처리(){
        //give
        Item item = new Item();
        item.setName("testResister2");

        Item item2 = new Item();
        item2.setName("testResister2");

        //when
        itemService.register(item);
        IllegalStateException e = assertThrows(IllegalStateException.class, () ->itemService.register(item));

        //then
        assertThat(e.getMessage()).isEqualTo("상품 코드가 중복됩니다.");

    }

    @Test
    public void 상품찾기(){

        List<Item> result = itemService.findItem();
        assertNotNull(result);

    }



}