package com.simso.controller.api;

import com.simso.dto.roadmap.RoadmapSaveRequestDto;
import com.simso.service.RoadmapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoadmapController {

    private final RoadmapService roadmapService;

    @PostMapping("api/v1/roadmap")
    public Long save(@RequestBody RoadmapSaveRequestDto requestDto) {
        return roadmapService.register(requestDto);
    }


}
