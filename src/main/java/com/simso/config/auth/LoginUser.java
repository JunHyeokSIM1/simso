package com.simso.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)   //어노테이션이 생성될수 있는 위치
@Retention(RetentionPolicy.RUNTIME)
public class LoginUser {
}
