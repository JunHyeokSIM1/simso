package com.simso.service;

import com.simso.domain.Roadmap;
import com.simso.domain.User;
import com.simso.dto.roadmap.RoadmapResponseDto;
import com.simso.dto.roadmap.RoadmapSaveRequestDto;
import com.simso.dto.roadmap.RoadmapUpdateRequestDto;
import com.simso.repository.RoadmapRepository;
import com.simso.repository.UserRepository;
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
    public List<RoadmapResponseDto> findAllDesc() {
        return  roadmapRepository.findALlDesc().stream()
                .map(RoadmapResponseDto::new)
                .collect(Collectors.toList());
    }

    /*
     *  로드맵 변경
     * */
    @Transactional
    public Long update(Long id, RoadmapUpdateRequestDto requestDto) {
        Roadmap roadmap = roadmapRepository.findById(id)
                .orElseThrow(()->
                        new IllegalArgumentException("해당 게시글 없습니다.id=" + id));

        roadmap.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }
}
