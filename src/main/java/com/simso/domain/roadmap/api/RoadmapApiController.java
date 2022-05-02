package com.simso.domain.roadmap.api;

import com.simso.domain.roadmap.dto.RoadmapResponseDto;
import com.simso.domain.roadmap.dto.RoadmapSaveRequestDto;
import com.simso.domain.roadmap.dto.RoadmapUpdateRequestDto;
import com.simso.domain.roadmap.service.RoadmapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoadmapApiController {

    private final RoadmapService roadmapService;

    @PostMapping("api/v1/roadmaps")
    public Long save(@RequestBody RoadmapSaveRequestDto requestDto) throws  Exception {
        return roadmapService.register(requestDto);
    }

    @GetMapping("api/v1/roadmaps")
    public ResponseEntity<List<RoadmapResponseDto>> findList() {
        return ResponseEntity.ok(roadmapService.findAllDesc());
    }

    @GetMapping("api/v1/roadmaps/{id}")
    public ResponseEntity<RoadmapResponseDto> findByRoadmap(@PathVariable Long id) {
        return ResponseEntity.ok(roadmapService.findById(id));
    }

    @PatchMapping("api/v1/roadmaps/{id}")
    public Long update(@PathVariable Long id, @RequestBody RoadmapUpdateRequestDto requestDto) throws  Exception{
        return roadmapService.update(id, requestDto);
    }

    @DeleteMapping("api/v1/roadmaps/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)  {
        roadmapService.remove(id);

        return ResponseEntity.ok().build();
    }

}
