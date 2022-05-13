package com.simso.domain.roadmap.service;

import com.simso.domain.roadmap.dto.RoadmapsResponseDto;
import com.simso.domain.roadmap.entity.Roadmap;
import com.simso.domain.user.entity.User;
import com.simso.domain.roadmap.exception.RoadmapNotFoundException;
import com.simso.domain.roadmap.dto.RoadmapResponseDto;
import com.simso.domain.roadmap.dto.RoadmapSaveRequestDto;
import com.simso.domain.roadmap.dto.RoadmapUpdateRequestDto;
import com.simso.domain.roadmap.repository.RoadmapRepository;
import com.simso.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoadmapService {

    private final RoadmapRepository roadmapRepository;
    private final UserRepository userRepository;

    /*
     *  로드맵 등록
     * */
    @Transactional
    public Long register(RoadmapSaveRequestDto requestDto) {
        Long userId = requestDto.getUserId();

        Optional<User> findByUser = userRepository.findById(userId);

        Roadmap save = roadmapRepository.save(
                Roadmap.createRoadmap(findByUser.orElse(null), requestDto));
        return save.getId();
    }

    /*
     *  로드맵 전부 조회
     *  */
    @Transactional(readOnly = true)
    public RoadmapsResponseDto findAllDesc() {
        return RoadmapsResponseDto.of(roadmapRepository.findALlDesc());
    }

    /*
     *  로드맵 단건 조회
     *  */
    @Transactional(readOnly = true)
    public RoadmapResponseDto findById(Long id) {
        return new RoadmapResponseDto(roadmapRepository.findById(id)
                .orElseThrow(RoadmapNotFoundException::new));
    }

    /*
     *  로드맵 변경
     * */
    @Transactional
    public Long update(Long id, RoadmapUpdateRequestDto requestDto) {
        Roadmap roadmap = roadmapRepository.findById(id)
                .orElseThrow(RoadmapNotFoundException::new);
        roadmap.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    /*
     * 로드맵 삭제
     */
    @Transactional
    public void remove(Long id) {
        roadmapRepository.deleteById(id);
    }

}
