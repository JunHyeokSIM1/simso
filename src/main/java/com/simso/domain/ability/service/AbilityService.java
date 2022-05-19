package com.simso.domain.ability.service;

import com.simso.domain.ability.entity.Ability;
import com.simso.domain.ability.repository.AbilityRepository;
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
    public Ability register(Long userId) {
        return abilityRepository.save(Ability.createAbility(findUser(userId)));
    }

    private User findUser(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new NoSuchElementException("user not found"));
    }
}
