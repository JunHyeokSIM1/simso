package com.simso.Controller.api;

import com.simso.domain.User;
import com.simso.dto.UserSaveRequestDto;
import com.simso.dto.UserUpdateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserApiControllerTest {

    @LocalServerPort //주입되는 port
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRepositoryOld userRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("등록된다")
    public void register() {
        //given
        String username = "등록API";
        String password = "1124";
        UserSaveRequestDto requestDto = UserSaveRequestDto.builder()
                .username(username)
                .password(password)
                .build();

        em.clear();

        String url = "http://localhost:" + port + "/api/user";

        //when
        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getUsername()).isEqualTo(username);
        assertThat(all.get(0).getPassword()).isEqualTo(password);

    }

    @Test
    @DisplayName("수정된다")
    @Transactional
    @Rollback(value = false)
    public void update() {
        //given
        User saveUser = userRepository.save(User.builder()
                .username("수정API")
                .password("1231")
                .build());


        Long updateId = saveUser.getId();
        String expectedUsername = "수정확인API";
        String expectedPassword = "123331";

        UserUpdateRequestDto requestDto = UserUpdateRequestDto.builder()
                .username(expectedUsername)
                .password(expectedPassword)
                .build();
        String url = "http://localhost:" + port + "/api/user/" + updateId;

        HttpEntity<UserUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = testRestTemplate.exchange(url, HttpMethod.PUT,
                requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getUsername()).isEqualTo(expectedUsername);
        assertThat(all.get(0).getPassword()).isEqualTo(expectedPassword);

    }


}