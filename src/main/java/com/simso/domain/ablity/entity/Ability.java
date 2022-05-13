package com.simso.domain.ablity.entity;

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

    @Column(name = "keyword_score")
    private int keywordScore;

    @Column(name = "information_power")
    private int informationPowerScore;

    @Column(name = "visitors")
    private int visitors;

    @Column(name = "update_frequency")
    private int updateFrequency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userAbility;

    @Builder
    public Ability(Long abilityId, int keywordScore, int informationPowerScore, int visitors, int updateFrequency, User user ) {
        this.abilityId = abilityId;
        this.keywordScore = keywordScore;
        this.informationPowerScore = informationPowerScore;
        this.visitors = visitors;
        this.updateFrequency = updateFrequency;
        this.userAbility = user;
    }
}
