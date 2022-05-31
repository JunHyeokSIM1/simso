package com.simso.domain.ability.entity;

import com.simso.domain.roadmap.entity.Roadmap;
import com.simso.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Ability {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ability_id")
    private  Long abilityId;

    @Column(name = "attack_power")
    private int attackPower;

    @Column(name = "defensive_power")
    private int defensivePower;

    @Column(name = "hit")
    private int hit;

    @Column(name = "critical")
    private int critical;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userAbility;

    @Builder
    public Ability(Long abilityId, int attackPower, int defensivePower, int hit, int critical, User userAbility) {
        this.abilityId = abilityId;
        this.attackPower = attackPower;
        this.defensivePower = defensivePower;
        this.hit = hit;
        this.critical = critical;
        this.userAbility = userAbility;
        //연관관계
        userAbility.getAbilities().add(this);

    }

    public static Ability createAbility(User userAbility) {
        return Ability.builder()
                .attackPower(1)
                .defensivePower(1)
                .hit(1)
                .critical(1)
                .userAbility(userAbility)
                .build();
    }

    /*  업데이트 */
    public void update (Ability ability) {
        this.abilityId = ability.abilityId;
        this.attackPower = ability.attackPower;
        this.defensivePower = ability.defensivePower;
        this.hit = ability.hit;
        this.critical = ability.critical;
        this.userAbility = ability.userAbility;
    }
}
