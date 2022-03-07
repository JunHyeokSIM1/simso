package com.simso.service;

import com.simso.domain.Item;
import com.simso.domain.User;
import com.simso.dto.UserSaveRequestDto;
import com.simso.repository.ItemRepository;
import com.simso.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        UserSaveRequestDto requestDto = new UserSaveRequestDto();

        //when
        Long saveId = userService.register(requestDto.builder()
                .username("등록테스트등")
                .password("1234")
                .build());

        //then
        User findUser = userService.findOne(saveId).get();
        System.out.println(findUser.getId());
        System.out.println(findUser.getUsername());
        System.out.println(requestDto.toEntity());

        //assertThat(requestDto).isEqualTo(findUser);
    }

    @Test
    @Transactional
    public  void 중복_예외_처리(){
        //given
        User user = new User();
       // user.setUsername("중복유저테스트");

        User user2 = new User();
      //  user.setUsername("중복유저테스트");

        //when
        //userService.register(user);
       // IllegalStateException e = assertThrows(IllegalStateException.class, () -> userService.register(user));
        //then
      //  assertThat(e.getMessage()).isEqualTo("user 이름이 중복됩니다");

    }

    @Test
    public void 모든유저찾기(){

        //given
        List<User> result = userService.findUser();

        //then
        for (User user : result) {
            System.out.println(user.getUsername());
        }

    }
    @Test
    @Transactional
    public  void 유저찾기(){
        //given
        User user = new User();
     //   user.setUsername("findTestUser");

        //when

       // Long save = userService.register(user);
          userService.findOne(user.getId());

       // User findUser = userService.findOne(save).get();

        //then
      //  assertThat(user.getUsername()).isEqualTo(findUser.getUsername());
    }

    @Test
    @Transactional
    public void 삭제(){

        //given
        User user = new User();
     //   user.setUsername("삭제테스트아이디");

       // userService.register(user);

      //  userService.DeleteUser(user.getId());

    }


}