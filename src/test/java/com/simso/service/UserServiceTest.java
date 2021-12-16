package com.simso.service;

import com.simso.domain.Item;
import com.simso.domain.User;
import com.simso.repository.ItemRepository;
import com.simso.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest

class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    @Test
    @Rollback(value = false)
    public void 등록(){
        //give
        User user = new User();
        user.setUsername("testUsername");

        //when
        Long saveId = userService.register(user);

        //then
        User findUser = userService.findOne(saveId).get();
        assertThat(user.getUsername()).isEqualTo(findUser.getUsername());
    }

//    @Test
//    @Transactional
//    public void 중복_예외_처리(){
//        //give
//        Item item = new Item();
//        item.setName("testResister2");
//
//        Item item2 = new Item();
//        item2.setName("testResister2");
//
//        //when
//        itemService.register(item);
//        IllegalStateException e = assertThrows(IllegalStateException.class, () ->itemService.register(item));
//
//        //then
//        assertThat(e.getMessage()).isEqualTo("상품 코드가 중복됩니다.");
//
//    }
//
//    @Test
//    public void 상품찾기(){
//
//        List<Item> result = itemService.findItem();
//        assertNotNull(result);
//
//    }
//


}