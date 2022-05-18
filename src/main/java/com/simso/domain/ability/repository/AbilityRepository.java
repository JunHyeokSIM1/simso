package com.simso.domain.ability.repository;

import com.simso.domain.ability.entity.Ability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbilityRepository extends JpaRepository<Ability, Long> {
}
