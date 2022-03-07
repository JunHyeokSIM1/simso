package com.simso.repository;

import com.simso.domain.User;
import org.junit.jupiter.api.Disabled;
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


        //when
        User user = userRepository.save(User.builder()
                .username("빌더테스트 생성자없이")
                .password("1234")
                .build());


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

        user.builder()
                .username("빌더테스트")
                .password("1234")
                .build();

        //when
        userRepository.save(user);

        User result = userRepository.findByname(user.getUsername()).get();

        //Then
        assertThat(user).isEqualTo(result);
    }

    @Test
    public void 전체조회(){

        //given
        User user = userRepository.save(User.builder()
                .username("조회 테스트")
                .password("1234")
                .build());

       //when
       List<User> result = userRepository.findAll();

       //then
        for (User user1 : result) {
            System.out.println(user1);
        }


    }
    @Test
    @Disabled
    public void 삭제(){

        User user = new User();
        //user.setUsername("deleteTest");

        userRepository.save(user);

        User result = userRepository.findByname(user.getUsername()).get();

        userRepository.delete(result.getId());

        //then

        if(result == null){
            System.out.println("ok");
        }

    }

    @Test
    @Disabled
    @Rollback(value = false)
    public void 업데이트(){

        User user = new User();
        //user.setUsername("update");

        userRepository.save(user);

        User result = userRepository.findByname("update").get();

       // result.setUsername("updateTest");

        User user1 = userRepository.updateByid(result);


        //when

        assertThat(result).isEqualTo(user1);

    }

}