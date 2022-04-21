package com.simso.domain.roadmap.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoadmapUpdateRequestDto {

    private String title;
    private String content;

    @Builder
    public RoadmapUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
