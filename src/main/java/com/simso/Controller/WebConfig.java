package com.simso.Controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void  addCrosMappings(CorsRegistry registry){
        registry.addMapping("/**")
                                   
                .allowCredentials(true);

    }
}
