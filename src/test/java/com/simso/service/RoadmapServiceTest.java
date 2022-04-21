package com.simso.service;

import com.simso.domain.Roadmap;
import com.simso.domain.Role;
import com.simso.domain.User;
import com.simso.dto.UserResponseDto;
import com.simso.dto.roadmap.RoadmapResponseDto;
import com.simso.dto.roadmap.RoadmapSaveRequestDto;
import com.simso.dto.roadmap.RoadmapUpdateRequestDto;
import com.simso.repository.RoadmapRepository;
import com.simso.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@SpringBootTest
class RoadmapServiceTest {

    @Autowired
    RoadmapService roadmapService;
    @Autowired
    RoadmapRepository roadmapRepository;
    @Autowired
    UserRepository userRepository;

    @AfterEach
    public void cleanup() {
        roadmapRepository.deleteAll();
    }

    @DisplayName("로드맵 기본필드를 등록한다")
    @Test
    public void register() {

        //given
        User user = createUser();
        Optional<User> findUser = userRepository.findById(user.getId());
        UserResponseDto responseDto = new UserResponseDto(findUser.orElse(null));
        RoadmapSaveRequestDto requestDto = createRoadmapRequestDto(responseDto);

        //when
        Long register = roadmapService.register(requestDto);

        //then
        Optional<Roadmap> findByRoadmap = roadmapRepository.findById(register);
        assertThat(register).isEqualTo(findByRoadmap.orElse(null).getId());

    }
    @DisplayName("등록된 로드맵을 조회한다")
    @Test
    public void findRoadmapList() {
        //given
        User user = createUser();
        Optional<User> findUser = userRepository.findById(user.getId());
        UserResponseDto responseDto = new UserResponseDto(findUser.orElse(null));

        RoadmapSaveRequestDto requestDto = createRoadmapRequestDto(responseDto);
        RoadmapSaveRequestDto requestDto2 = createRoadmapRequestDto(responseDto);
        roadmapService.register(requestDto);
        roadmapService.register(requestDto2);

        //when
        List<RoadmapResponseDto> findAll = roadmapService.findAllDesc();

        //then
        assertThat(findAll.size()).isEqualTo(2);
    }

    @DisplayName("수정한다 로드맵을")
    @Test
    public void update() {
        //given
        Long roadmapEntity = createRoadmapEntity();
        RoadmapUpdateRequestDto requestDto = new RoadmapUpdateRequestDto("업데이타이틀", "업데이트 컨텐트");

        //when
        Long updateId = roadmapService.update(roadmapEntity, requestDto);

        //then
        Optional<Roadmap> findId = roadmapRepository.findById(roadmapEntity);
        Optional<Roadmap> findUpdateId = roadmapRepository.findById(updateId);

        assertThat(findId.orElse(null).getContent()).
                isEqualTo(findUpdateId.orElse(null).getContent());

        assertThat(findId.orElse(null).getTitle()).
                isEqualTo(findUpdateId.orElse(null).getTitle());

    }

    @DisplayName("수정상황에서 IllegalArgumentException 예외 발생")
    @Test
    public void updateIllegalArgumentException() {
        //given
        Long roadmapEntity = createRoadmapEntity();
        RoadmapUpdateRequestDto requestDto = new RoadmapUpdateRequestDto("업데이타이틀", "업데이트 컨텐트");

        //when
        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> roadmapService.update(1000L, requestDto));
    }

    @DisplayName("삭제한다 로드맵을")
    @Test
    public void remove() {
        //given
        Long roadmapEntity = createRoadmapEntity();

        //when
        roadmapService.remove(roadmapEntity);

        //then
        Optional<Roadmap> findId = roadmapRepository.findById(roadmapEntity);

        assertThat(findId.orElse(null)).isNull();

    }

    @DisplayName("조회한다 단건 로드맵을")
    @Test
    public void findBySingleRoadMap() {
        //given
        Long roadmapEntity = createRoadmapEntity();

        //when
        RoadmapResponseDto singleRoadmap = roadmapService.findById(roadmapEntity);

        //then
        assertThat(singleRoadmap).isNotNull();
        assertThat(singleRoadmap.getId()).isEqualTo(roadmapEntity);

    }

    @DisplayName("조회에서 예외 NoSuchElementException 발생")
    @Test
    public void findBySingleRoadMapNoSuchElementException() {
        //given
        Long roadmapEntity = createRoadmapEntity();

        //when
        RoadmapResponseDto singleRoadmap = roadmapService.findById(roadmapEntity);

        //then
        assertThat(singleRoadmap).isNotNull();
        assertThat(singleRoadmap.getId()).isEqualTo(roadmapEntity);

    }

    // 로드맵 등록하고 저장하는 부분
    private Long createRoadmapEntity() {
        User user = createUser();
        Optional<User> findUser = userRepository.findById(user.getId());
        UserResponseDto responseDto = new UserResponseDto(findUser.orElse(null));

        RoadmapSaveRequestDto requestDto = createRoadmapRequestDto(responseDto);
        return roadmapService.register(requestDto);
    }

    // 로드맵 create save Request dto
    private RoadmapSaveRequestDto createRoadmapRequestDto(UserResponseDto responseDto) {
        return RoadmapSaveRequestDto.builder()
                .title("테스트 타이틀")
                .content("테스트 본문")
                .userId(responseDto.getId())
                .build();
    }

    // user create save Request dto
    private User createUser() {
        return userRepository.save(User.builder()
                .username("빌더테스트")
                .password("1234")
                .role(Role.USER)
                .build());
    }


}