package com.simso.domain.ability.dto;

import com.simso.domain.ability.entity.Ability;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AbilitiesResponseDto {

    private List<AbilityResponseDto> abilityResponseDtoList;

    public AbilitiesResponseDto(List<AbilityResponseDto> abilityResponseDtoList) {
        this.abilityResponseDtoList = abilityResponseDtoList;
    }

    public static AbilitiesResponseDto of(List<Ability> abilities) {
        return new AbilitiesResponseDto(toResponse(abilities));
    }

    public static List<AbilityResponseDto> toResponse(List<Ability> abilities) {
        return abilities.stream()
                .map(AbilityResponseDto::new)
                .collect(Collectors.toList());
    }
}
