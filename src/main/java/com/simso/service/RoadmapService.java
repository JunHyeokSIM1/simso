package com.simso.service;

import com.simso.domain.Roadmap;
import com.simso.domain.User;
import com.simso.dto.roadmap.RoadmapSaveRequestDto;
import com.simso.repository.RoadmapRepository;
import com.simso.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        Long userId = requestDto.getUserResponseDto().getId();

        Optional<User> findByUser = userRepository.findById(userId);

        Roadmap save = roadmapRepository.save(
                Roadmap.createRoadmap(findByUser.orElse(null), requestDto));
        return save.getId();
    }
}
