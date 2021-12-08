package com.simso.repository;

import com.simso.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class JpaUserRepositoryTest {

//    @Autowired
//    UserRepository userRepository;

    @Autowired
    JpaUserRepository userRepository;

    @Test
    @Rollback(value = false)
    public void save() {

        //given
        User user = new User();
        user.setUsername("testUserName");
        user.setPassword("12");

        //when
        userRepository.save(user);

        User result = userRepository.findByid(user.getId()).get();
        //.Optional get() Optional 에 저장되어 있는 객체 반환

        //Then
        assertThat(user).isEqualTo(result);

    }

    @Test
    @Rollback(value = false)
    public void findName(){
        //given

        User user = new User();
        user.setUsername("test12");

        //when
        userRepository.save(user);

        User result = userRepository.findByname(user.getUsername()).get();

        //Then
        assertThat(user).isEqualTo(result);
    }

    @Test
    public void 전체조회(){

       List<User> result = userRepository.findAll();

        for (User user: result) {
            System.out.println(user.getId() + user.getUsername() + user.getPassword());
        }

    }
    @Test
    public void 삭제(){

        User user = new User();
        user.setUsername("deleteTest");

        userRepository.save(user);

        User result = userRepository.findByname(user.getUsername()).get();

        userRepository.delete(result.getId());

        //then

        if(result == null){
            System.out.println("ok");
        }

    }

}