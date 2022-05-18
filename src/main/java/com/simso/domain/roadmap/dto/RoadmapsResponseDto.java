package com.simso.domain.roadmap.dto;

import com.simso.domain.roadmap.entity.Roadmap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoadmapsResponseDto {
    private List<RoadmapResponseDto> roadmapResponseList;

    public static RoadmapsResponseDto of(List<Roadmap> roadmaps) {
        return new RoadmapsResponseDto(toResponses(roadmaps));
    }

    private static List<RoadmapResponseDto> toResponses(List<Roadmap> roadmaps) {
        return roadmaps.stream()
                .map(RoadmapResponseDto::new)
                .collect(Collectors.toList());
    }
}
