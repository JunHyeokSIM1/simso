package com.simso.domain.ability.service;

import com.simso.domain.ability.dto.AbilitiesResponseDto;
import com.simso.domain.ability.dto.AbilityResponseDto;
import com.simso.domain.ability.dto.AbilityUpdateRequestDto;
import com.simso.domain.ability.entity.Ability;
import com.simso.domain.ability.exception.AbilityNotFondException;
import com.simso.domain.ability.repository.AbilityRepository;
import com.simso.domain.user.dto.UserResponseDto;
import com.simso.domain.user.entity.User;
import com.simso.domain.user.repository.UserRepository;
import com.simso.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AbilityService {
    private final AbilityRepository abilityRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long register(Long userId) {

        return abilityRepository
                .save(Ability.createAbility(findUser(userId)))
                .getAbilityId();
    }

    public AbilitiesResponseDto findAbilities() {
        return AbilitiesResponseDto.of(abilityRepository.findAll());
    }

    @Transactional
    public Long update(Long id, AbilityUpdateRequestDto requestDto) {
        Ability ability = abilityRepository.findById(id)
                .orElseThrow(AbilityNotFondException::new);
        ability.update(requestDto.toEntity());
        return ability.getAbilityId();
    }

    @Transactional
    public void remove(Long id) {
        abilityRepository.deleteById(id);
    }


    private User findUser(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new NoSuchElementException("user not found"));
    }
}
