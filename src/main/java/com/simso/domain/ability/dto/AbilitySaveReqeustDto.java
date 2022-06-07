package com.simso.domain.ability.dto;

import com.simso.domain.ability.entity.Ability;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class AbilitySaveReqeustDto {
    private int attackPower;
    private int defensivePower;
    private int hit;
    private int critical;

    @Builder
    public AbilitySaveReqeustDto(int attackPower, int defensivePower, int hit, int critical) {
        this.attackPower = attackPower;
        this.defensivePower = defensivePower;
        this.hit = hit;
        this.critical = critical;
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
