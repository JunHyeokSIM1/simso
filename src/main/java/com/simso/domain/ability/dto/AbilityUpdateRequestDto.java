package com.simso.domain.ability.dto;

import com.simso.domain.ability.entity.Ability;
import com.simso.domain.roadmap.entity.Roadmap;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AbilityUpdateRequestDto {

    private Long abilityId;
    private int attackPower;
    private int defensivePower;
    private int hit;
    private int critical;
    private int userAbility;

    @Builder
    public AbilityUpdateRequestDto(int attackPower,
                                   int defensivePower,
                                   int hit,
                                   int critical,
                                   int userAbility) {
        this.attackPower = attackPower;
        this.defensivePower = defensivePower;
        this.hit = hit;
        this.critical = critical;
        this.userAbility = userAbility;
    }

    public Ability toEntity() {
        return Ability.builder()
                .attackPower(attackPower)
                .defensivePower(defensivePower)
                .hit(hit)
                .critical(critical)
                .build();
    }

}
