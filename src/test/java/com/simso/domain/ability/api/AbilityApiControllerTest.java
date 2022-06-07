package com.simso.domain.ability.api;

import com.simso.domain.ability.entity.Ability;
import com.simso.domain.ability.repository.AbilityRepository;
import com.simso.domain.ability.service.AbilityService;
import com.simso.domain.user.entity.Role;
import com.simso.domain.user.entity.User;
import com.simso.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AbilityApiController.class)
class AbilityApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private AbilityRepository abilityRepository;

    @MockBean
    private AbilityService abilityService;

    @DisplayName("어빌리티 등록 테스트")
    @Test
    void register() throws Exception {
        //given
        User user = userRepository.save(User.builder()
                .username("빌더테스트")
                .password("1234")
                .role(Role.USER)
                .build());

        Ability save = abilityRepository.save(Ability.builder()
                .attackPower(1)
                .defensivePower(1)
                .hit(1)
                .critical(1)
                .userAbility(user)
                .build());
        given(abilityService.register(user.getId())).willReturn(save.getAbilityId());

        //when
        mvc.perform(post("/api/v1/ability"))
                .andExpect(status().isOk());



    }
}