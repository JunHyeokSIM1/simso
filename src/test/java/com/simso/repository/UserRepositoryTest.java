package com.simso.repository;

import com.simso.domain.user.entity.Role;
import com.simso.domain.user.entity.User;
import com.simso.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("저장한다")
    public void save() {

        //given
        User user = userRepository.save(User.builder()
                .username("빌더테스트")
                .password("1234")
                .role(Role.USER)
                .build());
        //when
        Optional<User> result = userRepository.findById(user.getId());

        //Then
        assertThat(user.getUsername()).isEqualTo(result.get().getUsername());

    }
}