package com.simso.repository;

import com.simso.domain.Item;
import com.simso.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
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
    public void 버전(){
        System.out.println(junit.runner.Version.id());

    }

}