package com.simso.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GEST("ROLE_GUSET", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;

}
