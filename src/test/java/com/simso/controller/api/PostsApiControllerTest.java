package com.simso.Controller.api;

import com.simso.domain.Posts;
import com.simso.dto.post.PostsSaveRequestDto;
import com.simso.dto.post.PostsUpdateRequestDto;
import com.simso.repository.PostsRepository;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {
    @LocalServerPort //주입되는 port
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("등록된다")
    public void register() {
        //given
        String title = "title";
        String content = "content";
        String author = "author";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();


        String url = "http://localhost:" + port + "/api/posts";

        //when
        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
        assertThat(all.get(0).getAuthor()).isEqualTo(author);

    }

    @Test
    @DisplayName("수정된다")
    @Transactional
    @Rollback(value = false)
    public void update() {
        //given
        String title = "title update";
        String content = "content update";
        String author = "author";

        Posts saveUser = postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build());


        Long updateId = saveUser.getId();
        String expectedTitle = "title update ex";
        String expectedContent = "content update ex";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(title)
                .content(content)
                .build();

        String url = "http://localhost:" + port + "/api/posts/" + updateId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = testRestTemplate.exchange(url, HttpMethod.PUT,
                requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }
}