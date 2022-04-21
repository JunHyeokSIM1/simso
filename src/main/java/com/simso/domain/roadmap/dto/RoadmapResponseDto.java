package com.simso.domain.roadmap.dto;

import com.simso.domain.roadmap.entity.Roadmap;
import lombok.Getter;


@Getter
public class RoadmapResponseDto {
    private Long id;
    private String title;
    private String content;

    public RoadmapResponseDto(Roadmap entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
