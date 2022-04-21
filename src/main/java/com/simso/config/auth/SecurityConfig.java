package com.simso.config.auth;

import com.simso.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity(debug = true) // spring Security 설정활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()  //h2-console 화면을 사용 하기 위해 해당 옵션들을 DISABLE 한다.
                .and()
                .authorizeRequests()  // URL별 권한 관리를 설정하는 옵션의 시작점 authorizeRequests 선언해야만 antMatchers 옵션사용 가능
                .antMatchers("/","/css/**", "/images/**",
                        "/js/**", "/h2-console").permitAll()            // 권한 관리 대상을 지정하는 옵션
                                                                        // URL HTTP 메소드별로 관리가 가능
                                                                        // "/" 등 지정된 URL들은 permiAll() 옵션을 통해 전체 열람 권한
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // USER  권한을 가진 사람만 가능하도록
                .anyRequest().authenticated() // 설정된 값들 이외 나머지 URL authenticated() 모두 인증된 사용자들에게 만 허용
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);

    }
}
