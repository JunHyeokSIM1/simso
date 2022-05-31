package com.simso.domain.ability.dto;

import com.simso.domain.ability.entity.Ability;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AbilityResponseDto {
    private Long abilityId;
    private int attackPower;
    private int defensivePower;
    private int hit;
    private int critical;

    public AbilityResponseDto(Ability ability) {
        this.abilityId = ability.getAbilityId();
        this.attackPower = ability.getAttackPower();
        this.defensivePower = ability.getDefensivePower();
        this.hit = ability.getHit();
        this.critical = ability.getCritical();
    }
}
