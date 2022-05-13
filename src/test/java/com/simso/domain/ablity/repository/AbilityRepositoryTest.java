package com.simso.domain.ablity.repository;

import com.simso.domain.ablity.entity.Ability;
import com.simso.domain.user.entity.Role;
import com.simso.domain.user.entity.User;
import com.simso.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AbilityRepositoryTest {
    @Autowired AbilityRepository abilityRepository;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("저장한다 능력치를")
    @Test
    @Rollback(value = false)
    void save() {
        //given
        User user = createUser();

        //when
        Ability save = abilityRepository.save(Ability.builder()
                .keywordScore(1)
                .informationPowerScore(2)
                .visitors(4)
                .updateFrequency(1)
                .user(user)
                .build());

        //then
        assertThat(save.getKeywordScore()).isEqualTo(1);
    }

    @DisplayName("조회한다 능력치를")
    @Test
    void  findByOne() {
        //given
        User user = createUser();
        Ability save = getAbility(user);

        //when
        Optional<Ability> findAbility = abilityRepository.findById(save.getAbilityId());

        //then
        assertThat(findAbility.isPresent()).isEqualTo(true);
    }

    @DisplayName("삭제한다 능력치를")
    @Test
    void  remove() {
        //given
        User user = createUser();
        Ability save = getAbility(user);
        Optional<Ability> findAbility = abilityRepository.findById(save.getAbilityId());
        //when

        abilityRepository.deleteById(findAbility.orElse(new Ability())
                .getAbilityId());

        //then
         findAbility = abilityRepository.findById(save.getAbilityId());

        assertThat(findAbility.isPresent()).isEqualTo(false);
    }

    private Ability getAbility(User user) {
        return abilityRepository.save(Ability.builder()
                    .keywordScore(1)
                    .informationPowerScore(1)
                    .visitors(1)
                    .updateFrequency(1)
                    .user(user)
                    .build());
    }

    // user create save Request dto
    private User createUser() {
        return userRepository.save(User.builder()
                .username("빌더테스트")
                .password("1234")
                .role(Role.USER)
                .build());
    }

}


