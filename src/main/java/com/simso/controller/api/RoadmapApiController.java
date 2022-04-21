package com.simso.controller.api;

import com.simso.dto.roadmap.RoadmapResponseDto;
import com.simso.dto.roadmap.RoadmapSaveRequestDto;
import com.simso.dto.roadmap.RoadmapUpdateRequestDto;
import com.simso.service.RoadmapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoadmapApiController {

    private final RoadmapService roadmapService;

    @PostMapping("api/v1/roadmaps")
    public Long save(@RequestBody RoadmapSaveRequestDto requestDto) {
        return roadmapService.register(requestDto);
    }

    @GetMapping("api/v1/roadmaps")
    public List<RoadmapResponseDto> findList() {
        return roadmapService.findAllDesc();
    }

    @PatchMapping("api/v1/roadmaps/{id}")
    public Long update(@PathVariable Long id, @RequestBody RoadmapUpdateRequestDto requestDto) throws  Exception{
        return roadmapService.update(id, requestDto);
    }

    @DeleteMapping("api/v1/roadmaps/{id}")
    public void delete(@PathVariable Long id) {
        roadmapService.remove(id);
    }
}
