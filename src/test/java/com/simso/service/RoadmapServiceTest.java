package com.simso.service;

import com.simso.domain.Roadmap;
import com.simso.domain.Role;
import com.simso.domain.User;
import com.simso.dto.UserResponseDto;
import com.simso.dto.roadmap.RoadmapSaveRequestDto;
import com.simso.repository.RoadmapRepository;
import com.simso.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class RoadmapServiceTest {

    @Autowired
    RoadmapService roadmapService;
    @Autowired
    RoadmapRepository roadmapRepository;
    @Autowired
    UserRepository userRepository;

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
        Assertions.assertThat(register).isEqualTo(findByRoadmap.orElse(null).getId());

    }

    private RoadmapSaveRequestDto createRoadmapRequestDto(UserResponseDto responseDto) {
        return RoadmapSaveRequestDto.builder()
                .title("테스트 타이틀")
                .content("테스트 본문")
                .userResponseDto(responseDto)
                .build();
    }


    private User createUser() {
        return userRepository.save(User.builder()
                .username("빌더테스트")
                .password("1234")
                .role(Role.USER)
                .build());
    }


}