package com.simso.domain.ability.api;

import com.simso.domain.ability.service.AbilityService;
import com.simso.domain.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AbilityApiController {

    private final AbilityService abilityService;

    @PostMapping("api/v1/ability")
    public ResponseEntity<Long> save(@RequestBody UserResponseDto responseDto){
        return ResponseEntity.ok(abilityService.register(responseDto.getId()));
    }

}
