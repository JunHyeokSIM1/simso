package com.simso.repository;

import com.simso.domain.roadmap.entity.Roadmap;
import com.simso.domain.user.entity.Role;
import com.simso.domain.user.entity.User;
import com.simso.domain.roadmap.repository.RoadmapRepository;
import com.simso.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RoadmapRepositoryTest {

    @Autowired
    private RoadmapRepository roadmapRepository;

    @Autowired
    private UserRepository userRepository;


    @DisplayName("로드맵 필드값을 저장한다")
    @Test
    public void save() {
        // given
        String title = "테스트 타이틀";
        String content = "테스트 본문";

        //when
        Roadmap saveRoadmap = roadmapRepository.save(Roadmap.builder()
                .title(title)
                .content(content)
                .build());
        //then
        assertThat(saveRoadmap.getTitle()).isEqualTo(title);
        assertThat(saveRoadmap.getContent()).isEqualTo(content);
    }

    @DisplayName("로드맵 필드갑과 맵핑된 유저 id를 저장한다")
    @Test
    public void userWithRoadmapSave() {
        // given
        String title = "테스트 타이틀";
        String content = "테스트 본문";

        User user = userRepository.save(User.builder()
                .username("빌더테스트")
                .password("1234")
                .role(Role.USER)
                .build());

        Roadmap saveRoadmap = roadmapRepository.save(Roadmap.builder()
                .title(title)
                .content(content)
                .user(user)
                .build());
        //when
        Optional<User> result = userRepository.findById(user.getId());

        //then
        assertThat(saveRoadmap.getTitle()).isEqualTo(title);
        assertThat(saveRoadmap.getContent()).isEqualTo(content);
        assertThat(saveRoadmap.getUser()).isNotNull();
    }

    @DisplayName("불러온다 1건 저장한 값")
    @Test
    public void getRoadmap() {
        String title = "테스트 타이틀";
        String content = "테스트 본문";

        User user = userRepository.save(User.builder()
                .username("빌더테스트")
                .password("1234")
                .role(Role.USER)
                .build());

        Roadmap saveRoadmap = roadmapRepository.save(Roadmap.builder()
                .title(title)
                .content(content)
                .user(user)
                .build());


        Optional<Roadmap> findRoadmap = roadmapRepository.findById(saveRoadmap.getId());
        assertThat(findRoadmap.isPresent()).isEqualTo(true);

        assertThat(findRoadmap.orElse(null).getTitle()).isEqualTo(title);
        assertThat(findRoadmap.orElse(null).getContent()).isEqualTo(content);

    }

    @DisplayName("불러온다 여러건 저장한 값")

    @Test
    public void getRoadmapList() {
        //given
        String title = "테스트 타이틀";
        String title2 = "테스트 타이틀2";
        String content = "테스트 본문";

        User user = userRepository.save(User.builder()
                .username("빌더테스트")
                .password("1234")
                .role(Role.USER)
                .build());

        Roadmap saveRoadmap = roadmapRepository.save(Roadmap.builder()
                .title(title)
                .content(content)
                .user(user)
                .build());

        Roadmap saveRoadmap2 = roadmapRepository.save(Roadmap.builder()
                .title(title2)
                .content(content)
                .user(user)
                .build());

        //when
        List<Roadmap> list = roadmapRepository.findAll();

        //then
        assertThat(list.size()).isEqualTo(2);
    }


}