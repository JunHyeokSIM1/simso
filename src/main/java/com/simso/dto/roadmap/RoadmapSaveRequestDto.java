package com.simso.dto.roadmap;

import com.simso.domain.Roadmap;
import com.simso.dto.UserResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoadmapSaveRequestDto {
    private String title;
    private String content;
    private UserResponseDto userResponseDto;

    @Builder
    public RoadmapSaveRequestDto(String title, String content, UserResponseDto userResponseDto) {
        this.title = title;
        this.content = content;
        this.userResponseDto = userResponseDto;
    }

    public Roadmap toEntity() {
        return Roadmap.builder()
                .title(title)
                .content(content)
                .build();
    }
}
